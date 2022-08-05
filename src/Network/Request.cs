using System.Net;
using System.Net.NetworkInformation;
using System.Text;

using ServerCheck.Config;

namespace ServerCheck.Network
{
    static class Request
    {
        private static readonly int _httpTimeout = ConfigManager.GetAppSettingInt("httpTimeout");
        private static readonly int _pingTimeout = ConfigManager.GetAppSettingInt("pingTimeout");

        public static bool HttpRequest(string url, string method)
        {
            try
            {
                WebRequest request = WebRequest.Create(url);
                request.Method = method;
                request.ContentType = "application/x-www-form-urlencoded";
                request.Timeout = Request._httpTimeout * 1000;

                WebResponse response = request.GetResponse();
                string statusDescription = ((HttpWebResponse)response).StatusDescription;
                response.Close();

                return statusDescription == "OK";
            }
            catch
            {
                return false;
            }
        }

        public static bool PingRequest(string ip)
        {
            try
            {
                string data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                byte[] buffer = Encoding.ASCII.GetBytes(data);

                Ping pingSender = new Ping();
                IPAddress address = IPAddress.Parse(ip);
                PingOptions options = new PingOptions(64, true);
                PingReply reply = pingSender.Send(address, Request._pingTimeout * 1000, buffer, options);

                return reply.Status == IPStatus.Success;
            }
            catch
            {
                return false;
            }
        }

    }
}
