using System.Data;

using MySql.Data.MySqlClient;

using ServerCheck.Config;

namespace ServerCheck.Database
{
    class MySql : Database
    {
        private MySqlConnection _conn;

        public MySql()
        {
            this._connectionString = ConfigManager.GetConnectionString("mysql_server_check");
        }

        protected override bool IsOpen()
        {
            return this._conn != null && this._conn.State == ConnectionState.Open;
        }

        protected override void Connect()
        {
            this._conn = new MySqlConnection(_connectionString);
        }

        protected override void Disconnect()
        {
            this._conn.Close();
        }

        public override DataTable GetRows(string fields, string tables)
        {
            if (this.IsOpen() == false) this.Connect();

            string query = "SELECT " + fields + " FROM " + tables;
            MySqlCommand cmd = new MySqlCommand(query, this._conn);

            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable data = new DataTable();

            adapter.SelectCommand = cmd;
            adapter.Fill(data);

            this.Disconnect();

            return data;
        }
    }
}
