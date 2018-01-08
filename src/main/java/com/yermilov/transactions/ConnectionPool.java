package com.yermilov.transactions;

import org.apache.commons.dbcp2.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool connectionPool = new ConnectionPool();
    private static BasicDataSource dataSource;

    private ConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/taxisystemdb?autoReconnect=true&useSSL=false&useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(180);
    }
    public static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            return connectionPool;
        } else {
            return connectionPool;
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
