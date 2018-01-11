package com.yermilov.dao;

import com.yermilov.domain.Client;
import com.yermilov.domain.Ride;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public Ride update(Ride entity) {
        return null;
    }

    public double getMoneySpentForClient(Client client){
        return 0;
    }
}
