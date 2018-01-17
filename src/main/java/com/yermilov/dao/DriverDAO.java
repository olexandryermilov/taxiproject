package com.yermilov.dao;

import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDAO extends AbstractDAO<Driver> {
    private final static Logger LOGGER = LoggerFactory.getLogger(DriverDAO.class);
    public DriverDAO(){

    }
    @Override
    public List<Driver> findAll() {
        return null;
    }

    private final static String SQL_SELECT_BY_USERID="select * from driver where userid=?";

    public Driver findByUserId(int userid) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_USERID);
                statement.setInt(1, userid);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
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
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
    }
    private final static String SQL_SELECT_BY_ID="select * from driver where driverid=?";
    @Override
    public Driver findById(int id) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_ID);
                statement.setInt(1, id);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Driver driver = new Driver(resultSet.getInt("userid"));
                    driver.setDriverId(id);
                    return driver;
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
    public boolean delete(int id) throws DAOException {
       return false;
    }

    private final static String SQL_DELETE_BY_USERID = "delete from driver where userid=?";
    @Override
    public boolean delete(Driver entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_DELETE_BY_USERID);
                statement.setInt(1,entity.getUserId());
                LOGGER.debug("Statement to execute {}",statement.toString());
                return statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }
    private final static String SQL_INSERT_DRIVER = "insert into driver(userid) values(?)";
    @Override
    public boolean create(Driver entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_INSERT_DRIVER);
                statement.setInt(1, entity.getUserId());
                LOGGER.debug("Statement to execute {}", statement.toString());
                return statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        finally {
        }
    }

    @Override
    public boolean update(Driver entity) {
        return false;
    }
}
