using ServerCheck.Config;
using ServerCheck.Sender;

namespace ServerCheck.Daemon
{
    class AlertMessage
    {
        private static readonly string _errorMessage = ConfigManager.GetAppSetting("errorMessage");
        private static readonly string _solveMessage = ConfigManager.GetAppSetting("solveMessage");

        private readonly bool _outcome;
        private readonly string _serverAddress;
        private readonly string _checkTimestamp;

        private string _text = "";

        public AlertMessage(bool outcome, string server_address, string check_timestamp)
        {
            this._outcome = outcome;
            this._serverAddress = server_address;
            this._checkTimestamp = check_timestamp;

            this.GenerateText();
        }

        private void GenerateText()
        {
            this._text = this._outcome ? AlertMessage._solveMessage : AlertMessage._errorMessage;
            this._text = this._text.Replace("[SERVER_ADDRESS]", this._serverAddress).Replace("[DATE]", this._checkTimestamp);
        }

        public void Notify(string to, string contact_type)
        {
            ISender sender = SenderCreator.GetSender(contact_type);
            sender.Send(to, this._text);
        }
    }
} 