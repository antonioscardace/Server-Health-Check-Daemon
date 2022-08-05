namespace ServerCheck.Sender
{
    interface ISender
    {
        public void Send(string recipient, string text_message);
    }
}
