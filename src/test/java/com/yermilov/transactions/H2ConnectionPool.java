package com.yermilov.transactions;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionPool implements ConnectionPool {
    private final static ConnectionPool connectionPool = new H2ConnectionPool();
    private DataSource dataSource;
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private H2ConnectionPool(){
        try{
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource = JdbcConnectionPool.create(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
        //return dataSource.getConnection();
    }

    public static ConnectionPool getInstance() {
        return connectionPool;
    }
}
