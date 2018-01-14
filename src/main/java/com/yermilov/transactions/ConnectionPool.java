package com.yermilov.transactions;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
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
    public static ConnectionPool getInstance() throws SQLException {
        return connectionPool;
    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("Giving a connection");
        return dataSource.getConnection();
    }

}
