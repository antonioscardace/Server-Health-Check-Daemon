using System.Net;
using System.Net.Mail;

using ServerCheck.Config;

namespace ServerCheck.Sender
{
    class EmailSender : ISender
    {
        private static readonly string _emailSubject = ConfigManager.GetAppSetting("emailSubject");
        private static readonly string _senderEmail = ConfigManager.GetAppSetting("senderEmail");
        private static readonly string _senderName = ConfigManager.GetAppSetting("senderName");
        private static readonly string _senderUsername = ConfigManager.GetAppSetting("senderUsername");
        private static readonly string _senderPassword = ConfigManager.GetAppSetting("senderPassword");
        private static readonly string _smtpHost = ConfigManager.GetAppSetting("smtpHost");
        private static readonly int _smtpPort = ConfigManager.GetAppSettingInt("smtpPort");

        public void Send(string recipient, string text_message)
        {
            MailMessage newMail = new MailMessage();
            newMail.From = new MailAddress(EmailSender._senderEmail, EmailSender._senderName);
            newMail.IsBodyHtml = true;
            newMail.Body = text_message;
            newMail.Subject = EmailSender._emailSubject;
            newMail.To.Add(recipient);

            SmtpClient client = new SmtpClient(EmailSender._smtpHost, EmailSender._smtpPort);
            client.EnableSsl = true;
            client.Credentials = new NetworkCredential(EmailSender._senderUsername, EmailSender._senderPassword);

            client.Send(newMail);
        }
    }
}