package com.yermilov.transactions;

import com.yermilov.exceptions.TransactionException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);
    private static ThreadLocal<ConnectionWrapper> threadLocal = new ThreadLocal<>();
    private static ConnectionPool connectionPool = MySQLConnectionPool.getInstance();

    public static void setConnectionPool(ConnectionPool connectionPool) {
        TransactionManager.connectionPool = connectionPool;
    }

    private TransactionManager() {
    }

    public static void beginTransaction() throws SQLException, TransactionException {
        if (threadLocal.get()!=null)
            throw new TransactionException();
        Connection connection = connectionPool.getConnection();
        ConnectionWrapper wrapper = new ConnectionWrapper(connection,true);
        threadLocal.set(wrapper);
    }

    public static void endTransaction() throws SQLException, TransactionException {
        if (threadLocal.get()==null)
            throw new TransactionException();
        ConnectionWrapper wrapper = threadLocal.get();
        wrapper.getConnection().close();
        threadLocal.set(null);
    }

    public static ConnectionWrapper getConnection() throws SQLException {
        if (threadLocal.get()==null){
            Connection connection = connectionPool.getConnection();
            LOGGER.info("Connection given");
            ConnectionWrapper wrapper = new ConnectionWrapper(connection,false);
            return wrapper;
        } else {return threadLocal.get();}
    }
}
