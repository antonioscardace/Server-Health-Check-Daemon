package com.antonioscardace.app.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Database {
    protected Integer port;
    protected String server;

    protected String userid;
    protected String password;

    protected String dbName;
    protected Connection connection;

    protected Database(String server, int port, String user, String password, String dbName) throws ClassNotFoundException, SQLException {
        this.port = port;
        this.server = server;
        this.userid = user;
        this.password = password;
        this.dbName = dbName;
        this.connection = null;

        this.connect();
    }

    protected boolean isConnected() throws SQLException {
        return this.connection != null && !this.connection.isClosed();
    }

    protected void disconnect() throws SQLException {
        this.connection.close();
        this.connection = null;
    }

    protected abstract void connect()
            throws ClassNotFoundException, SQLException;

    public abstract int deleteRow(String table, String condition)
            throws ClassNotFoundException, SQLException;

    public abstract int insertRow(String table, String fields, String values)
            throws ClassNotFoundException, SQLException;

    public abstract int updateRow(String table, String values, String condition)
            throws ClassNotFoundException, SQLException;

    public abstract ResultSet getRows(String table, String fields, String condition, String orderBy)
            throws ClassNotFoundException, SQLException;

    public abstract ResultSet getRows(String table, String fields, String condition)
            throws ClassNotFoundException, SQLException;

    public abstract ResultSet getRows(String table, String fields)
            throws ClassNotFoundException, SQLException;

    public abstract ResultSet getRows(String table)
            throws ClassNotFoundException, SQLException;
}