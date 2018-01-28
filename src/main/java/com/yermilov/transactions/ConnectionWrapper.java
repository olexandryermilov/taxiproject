package com.yermilov.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {
    private Connection connection;
    private boolean isTransaction;

    public ConnectionWrapper(Connection connection, boolean isTransaction) {
        this.connection = connection;
        this.isTransaction = isTransaction;
    }

    public void close() throws SQLException {
        if (!isTransaction){
            connection.close();
        }
    }

    Connection getConnection(){
        return connection;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
