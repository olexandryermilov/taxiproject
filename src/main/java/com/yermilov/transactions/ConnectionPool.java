package com.yermilov.transactions;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static BasicDataSource dataSource;// =... ;

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
