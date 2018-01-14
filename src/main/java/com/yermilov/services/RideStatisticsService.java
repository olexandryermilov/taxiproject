package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.RideDAO;
import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Ride;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

public class RideStatisticsService {
    private final static Logger logger = LoggerFactory.getLogger(RideStatisticsService.class);
    private final static RideStatisticsService RIDE_STATISTICS_SERVICE = new RideStatisticsService();
    private IDAOFactory daoFactory;
    private RideStatisticsService(){
        daoFactory=DAOFactory.getInstance();
    }
    public static RideStatisticsService getRideStatisticsService(){
        return RIDE_STATISTICS_SERVICE;
    }

    public double getClientsSpentMoney(Client client) throws DAOException {
        RideDAO rideDAO = daoFactory.getRideDAO();
        double moneySpent = rideDAO.getMoneySpentForClient(client);
        return moneySpent;
    }

    public boolean putRideToDatabase(Client client, Driver driver, Taxi taxi, double cost, double distance,
                                       Date rideStart, Date rideFinish) throws DAOException{
        System.out.println(driver);
        System.out.println(client);
        System.out.println(taxi);
        Ride ride = new Ride(driver.getDriverId(),client.getClientId(),taxi.getTaxiId(),cost,distance,rideStart,rideFinish);
        return putRideToDatabase(ride);
    }
    public boolean putRideToDatabase(Ride ride)throws DAOException{
        RideDAO rideDAO = daoFactory.getRideDAO();
        return rideDAO.create(ride);
    }
}
