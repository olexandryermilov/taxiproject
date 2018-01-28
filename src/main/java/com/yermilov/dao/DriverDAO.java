package com.yermilov.dao;

import com.yermilov.domain.Driver;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(DriverDAO.class);
    private final static String SQL_SELECT_BY_USERID="select * from driver where userid=?";
    private final static String SQL_SELECT_BY_ID="select * from driver where driverid=?";
    private final static String SQL_DELETE_BY_USERID = "delete from driver where userid=?";
    private final static String SQL_INSERT_DRIVER = "insert into driver(userid) values(?)";
    public DriverDAO(){

    }

    public Driver findByUserId(int userid) throws DAOException {
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
                    Driver driver = new Driver(userid);
                    driver.setDriverId(resultSet.getInt("driverid"));
                    return driver;
                }
                return null;
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
    }

    public Driver findById(int id) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            ResultSet resultSet=null;
            try {
                statement = con.preparedStatement(SQL_SELECT_BY_ID);
                statement.setInt(1, id);
                LOGGER.debug("Statement to execute {}",statement.toString());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Driver driver = new Driver(resultSet.getInt("userid"));
                    driver.setDriverId(id);
                    return driver;
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

    public boolean delete(Driver entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            try {
                statement = con.preparedStatement(SQL_DELETE_BY_USERID);
                statement.setInt(1,entity.getUserId());
                LOGGER.debug("Statement to execute {}",statement.toString());
                return statement.execute();
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

    public boolean create(Driver entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            try {
                statement = con.preparedStatement(SQL_INSERT_DRIVER);
                statement.setInt(1, entity.getUserId());
                LOGGER.debug("Statement to execute {}", statement.toString());
                return statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                if(statement!=null)statement.close();
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        finally {
        }
    }
}
