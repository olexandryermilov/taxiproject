package com.yermilov.transactions;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionPool implements ConnectionPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(MySQLConnectionPool.class);
    private static MySQLConnectionPool connectionPool = new MySQLConnectionPool();
    private static  PoolingDataSource<PoolableConnection> dataSource;

    private MySQLConnectionPool() {
        initDataSource();
    }
    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    private void initDataSource() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/taxisystemdb?autoReconnect=true&useSSL=false&useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);

        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);

        poolableConnectionFactory.setPool(connectionPool);

        dataSource =
                new PoolingDataSource<>(connectionPool);
    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("Giving a connection");
        return dataSource.getConnection();
    }

}
