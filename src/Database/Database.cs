using System.Data;

namespace ServerCheck.Database
{
    abstract class Database
    {
        protected string _connectionString;

        protected abstract bool IsOpen();
        protected abstract void Connect();
        protected abstract void Disconnect();

        public abstract DataTable GetRows(string fields, string tables);
    }
}
