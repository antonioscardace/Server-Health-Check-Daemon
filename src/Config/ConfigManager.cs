using System.Configuration;

namespace ServerCheck.Config
{
    static class ConfigManager
    {
        public static string GetConnectionString(string key)
        {
            return ConfigurationManager.ConnectionStrings[key].ConnectionString;
        }

        public static string GetAppSetting(string key)
        {
            return ConfigurationManager.AppSettings[key];
        }

        public static int GetAppSettingInt(string key)
        {
            return int.Parse(ConfigurationManager.AppSettings[key]);
        }
    }
}