package com.yermilov.dao;

import com.yermilov.domain.Taxi;
import com.yermilov.domain.User;
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

    private final static String SQL_SELECT_BY_TAXIID = "select * from taxi where taxiid=?";
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
    public boolean create(Taxi entity) {
        return false;
    }

    @Override
    public Taxi update(Taxi entity) {
        return null;
    }
}
