namespace ServerCheck.Sender
{
    static class SenderCreator
    {
        public static ISender GetSender(string contact_type)
        {
            return contact_type switch
            {
                "email" => new EmailSender(),
                "telegram" => new TelegramSender(),
                _ => null
            };
        }
    }
}