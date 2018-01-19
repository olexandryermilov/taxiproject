package com.yermilov.transactions;

//import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class H2ConnectionPool implements ConnectionPool {
    private final static ConnectionPool connectionPool = new H2ConnectionPool();
   // private JdbcConnectionPool cp;
    private H2ConnectionPool(){
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
        }
        //cp= JdbcConnectionPool.create("jdbc:h2:~/test","sa","");
    }
    @Override
    public Connection getConnection() throws SQLException {
        return null;//cp.getConnection();
    }

    public static ConnectionPool getInstance() {
        return connectionPool;
    }
}
