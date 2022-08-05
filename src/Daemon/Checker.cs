using System;
using System.Data;
using System.Threading;
using System.Collections.Generic;

using ServerCheck.Network;

namespace ServerCheck.Daemon
{
    sealed class Checker
    {
        private static readonly Checker _instance = new Checker();

        private Checker()
        {
        } 

        public static Checker GetInstance()
        {
            return _instance;
        }

        private bool CheckConnection(string address_type, string address)
        {
            return address_type switch
            {
                "ip" => Request.PingRequest(address),
                "url" => Request.HttpRequest(address, "GET"),
                _ => false
            };
        }

        public void Run(int checkTime)
        {
            HashSet<Tuple<string, string>> notified = new HashSet<Tuple<string, string>>();
            Database.MySql db = new Database.MySql();

            while (true)
            {
                DataTable data = db.GetRows(
                    "s.address, s.address_type, a.contact, a.contact_type",
                    "servers s JOIN observes o ON s.address = o.address JOIN accounts a ON a.contact = o.contact"
                );

                foreach (DataRow row in data.Rows)
                {
                    string serverAddress = row["address"].ToString();
                    string serverAddressType = row["address_type"].ToString();
                    string receiverContact = row["contact"].ToString();
                    string receiverContactType = row["contact_type"].ToString();

                    string checkTimestamp = DateTime.UtcNow.ToString("s") + " UTC";
                    bool isConnected = this.CheckConnection(serverAddressType, serverAddress);

                    Tuple<string, string> serverObserver = new Tuple<string, string>(serverAddress, receiverContact);
                    bool wasUnhealthy = notified.Contains(serverObserver);
                                     
                    if ((isConnected && wasUnhealthy) || (!isConnected && !wasUnhealthy))
                    {
                        AlertMessage newAlert = new AlertMessage(isConnected, serverAddress, checkTimestamp);
                        newAlert.Notify(receiverContact, receiverContactType);
                        
                        if (isConnected && wasUnhealthy) notified.Remove(serverObserver);
                        else if (!isConnected && !wasUnhealthy) notified.Add(serverObserver);
                    }
                }

                Thread.Sleep(checkTime * 1000);
            }
        }

    }
}
