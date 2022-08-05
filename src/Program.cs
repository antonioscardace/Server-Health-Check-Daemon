using ServerCheck.Config;
using ServerCheck.Daemon;

namespace ServerCheck
{
    class Program
    {
        private static readonly int _checkTime = ConfigManager.GetAppSettingInt("checkTime");

        public static void Main()
        {
            Checker c = Checker.GetInstance();
            c.Run(Program._checkTime);
        }
    }
}