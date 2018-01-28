package com.yermilov.dao;

import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientDAO.class);
    private final static String SQL_DELETE_BY_USERID = "delete from client where userid=?";
    private static final String SQL_INSERT_CLIENT="insert into client(userid) values(?)";
    private final static String SQL_SELECT_BY_USERID = "select * from client where userid=?";
    ClientDAO(){}
    public Client findClientByUserId(int userid) throws DAOException{
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            ResultSet resultSet=null;
            try {
                statement = con.preparedStatement(SQL_SELECT_BY_USERID);
                statement.setInt(1, userid);
                LOGGER.debug("Statement to execute {}",statement.toString());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Client client = new Client(userid);
                    client.setClientId(resultSet.getInt("clientid"));
                    return client;
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet!=null)resultSet.close();
                if(statement!=null)statement.close();
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
        return null;
    }

    public boolean delete(Client entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            try { statement = con.preparedStatement(SQL_DELETE_BY_USERID);
                statement.setInt(1,entity.getUserId());
                LOGGER.debug("Statement to execute {}",statement.toString());
                statement.execute();

                return true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
            finally {
                if(statement!=null)statement.close();
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    public boolean create(Client entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            try {
                statement = con.preparedStatement(SQL_INSERT_CLIENT);
                statement.setInt(1, entity.getUserId());
                LOGGER.debug("Statement to execute {}",statement.toString());
                statement.execute();
                statement.close();
                return true;
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                if(statement!=null)statement.close();
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }

    }

}
