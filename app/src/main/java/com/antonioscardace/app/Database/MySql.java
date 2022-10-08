package com.antonioscardace.app.Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.MessageFormat;

public class MySql extends Database {

    public MySql(String server, int port, String user, String password, String dbName) throws ClassNotFoundException, SQLException {
        super(server, port, user, password, dbName);
    }

    protected void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", this.server, this.port.toString(), this.dbName);
        this.connection = DriverManager.getConnection(url, this.userid, this.password);
    }

    private int updateData(String sql) throws SQLException, ClassNotFoundException {
        Statement stmt = this.connection.createStatement();
        return stmt.executeUpdate(sql);
    }

    private ResultSet getData(String sql) throws SQLException, ClassNotFoundException {
        Statement stmt = this.connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public int deleteRow(String table, String condition) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("DELETE FROM {0} WHERE {1}", table, condition);
        return this.updateData(sql);
    }

    public int insertRow(String table, String fields, String values) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("INSERT INTO {0} ({1}) VALUES {2}", table, fields, values);
        return this.updateData(sql);
    }

    public int updateRow(String table, String values, String condition) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("UPDATE {0} SET {1} WHERE {2}", table, values, condition);
        return this.updateData(sql);
    }

    public ResultSet getRows(String table, String fields, String condition, String orderBy) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("SELECT {0} FROM {1} WHERE {2} ORDER BY {3}", fields, table, condition, orderBy);
        return this.getData(sql);
    }

    public ResultSet getRows(String table, String fields, String condition) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("SELECT {0} FROM {1} WHERE {2}", fields, table, condition);
        return this.getData(sql);
    }

    public ResultSet getRows(String table, String fields) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("SELECT {0} FROM {1}", fields, table);
        return this.getData(sql);
    }

    public ResultSet getRows(String table) throws ClassNotFoundException, SQLException {
        String sql = MessageFormat.format("SELECT * FROM {0}", table);
        return this.getData(sql);
    }
}