using Telegram.Bot;
using Telegram.Bot.Types;
using Telegram.Bot.Types.Enums;

using ServerCheck.Config;

namespace ServerCheck.Sender
{
    class TelegramSender : ISender
    {
        private static readonly string _token = ConfigManager.GetAppSetting("telegramToken");

        public async void Send(string recipient, string text_message)
        {
            TelegramBotClient bot = new TelegramBotClient(TelegramSender._token);
            Message message = await bot.SendTextMessageAsync(recipient, text_message, parseMode: ParseMode.Html);
        }
    }
}