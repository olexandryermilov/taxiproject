package com.yermilov.dao;

import com.yermilov.domain.Client;
import com.yermilov.domain.Ride;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RideDAO extends AbstractDAO<Ride> {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideDAO.class);
    @Override
    public List<Ride> findAll() {
        return null;
    }

    @Override
    public Ride findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Ride entity) {
        return false;
    }

    private final static String SQL_INSERT="insert into ride(driverId,clientId,taxiId,cost,distance,rideStart,rideFinish)" +
            " values(?,?,?,?,?,?,?)";
    @Override
    public boolean create(Ride entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_INSERT);
                statement.setInt(1,entity.getDriverId());
                statement.setInt(2,entity.getClientId());
                statement.setInt(3,entity.getTaxiId());
                statement.setDouble(4,entity.getCost());
                statement.setDouble(5,entity.getDistance());
                statement.setDate(6,entity.getRideStart());
                statement.setDate(7,entity.getRideFinish());
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

    @Override
    public boolean update(Ride entity) {
        return false;
    }

    private final static String SQL_GET_MONEY_FOR_CLIENT = "select sum(cost) from ride where clientid=?";
    public double getMoneySpentForClient(Client client) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_GET_MONEY_FOR_CLIENT);
                statement.setInt(1, client.getClientId());
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getDouble("sum(cost)");
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
            return 0.0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
    }
    private final static String SQL_SELECT_ALL_CLIENTS_RIDES = "select * from ride where clientid=?";
    //driverId,clientId,taxiId;
    //private double cost, distance;
    //private Date rideStart, rideFinish;
    public List<Ride> findRidesForClient(Client client) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_ALL_CLIENTS_RIDES);
                statement.setInt(1, client.getClientId());
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                List<Ride> rides = new ArrayList<>();
                while (resultSet.next()) {
                    rides.add(new Ride(resultSet.getInt("driverid"), resultSet.getInt("clientId"),
                            resultSet.getInt("taxiid"), resultSet.getDouble("cost"),
                            resultSet.getDouble("distance"),resultSet.getDate("ridestart"),
                            resultSet.getDate("ridefinish")));
                }
                return rides;
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
}
