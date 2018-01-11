package com.yermilov.dao;

import com.yermilov.domain.Client;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientDAO.class);
    private static final String SQL_SELECT_ALL = "select * from taxisystemdb.client";
    private final static String SQL_SELECT_BY_ID = "select * from taxisystemdb.client where clientid=?";
    private final static String SQL_DELETE_BY_ID = "delete from taxisystemdb.client where clientid=?";
    private static final String SQL_INSERT_CLIENT="insert into taxisystemdb.client(userid) values(?)";
    ClientDAO(){}
    private final static String SQL_SELECT_BY_USERID = "select * from taxisystemdb.client where userid=?";
    public Client findClientByUserId(int userid) throws DAOException{
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_USERID);
                statement.setInt(1, userid);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Client client = new Client(userid);
                    client.setClientId(resultSet.getInt("clientid"));
                    return client;
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
        return null;
    }
    @Override
    public List<Client> findAll() {
        return null;
    }


    @Override
    public Client findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Client entity) {
        return false;
    }


    @Override
    public boolean create(Client entity) {
        return false;
    }

    @Override
    public Client update(Client entity) {
        throw new UnsupportedOperationException();
    }

}
