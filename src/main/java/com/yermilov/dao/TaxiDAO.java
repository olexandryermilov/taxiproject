package com.yermilov.dao;

import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaxiDAO extends AbstractDAO<Taxi> {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaxiDAO.class);
    private final static String SQL_SELECT_BY_CARNUMBER = "select * from taxi where carnumber=?";
    private final static String SQL_COUNT_SIZE = "select count(*) from taxi";
    private final static String SQL_SELECT_BY_TAXIID = "select * from taxi where taxiid=?";
    private final static String SQL_INSERT_TAXI = "insert into taxi(carnumber,taxitypeid,driverid) values(?,?,?)";
    public int findSize() throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_COUNT_SIZE);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
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
        return -1;
    }
    public Taxi findByCarNumber(String carNumber) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_CARNUMBER);
                statement.setString(1, carNumber);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Taxi taxi = new Taxi(resultSet.getInt("driverid"),
                            resultSet.getInt("taxitypeid"),carNumber);
                    taxi.setTaxiId(resultSet.getInt("taxiid"));
                    return taxi;
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
    }
    @Override
    public List<Taxi> findAll() {
        return null;
    }

    @Override
    public Taxi findById(int id) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_TAXIID);
                statement.setInt(1, id);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Taxi taxi = new Taxi(resultSet.getInt("driverid"),
                            resultSet.getInt("taxitypeid"),resultSet.getString("carnumber"));
                    taxi.setTaxiId(id);
                    return taxi;
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Taxi entity) {
        return false;
    }

    @Override
    public boolean create(Taxi entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_INSERT_TAXI);
                statement.setString(1, entity.getCarNumber());
                statement.setInt(2, entity.getTaxiTypeId());
                statement.setInt(3, entity.getDriverId());
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
    public boolean update(Taxi entity) {
        return false;
    }
}
