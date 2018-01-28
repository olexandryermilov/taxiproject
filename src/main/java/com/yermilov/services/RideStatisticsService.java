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
import java.util.List;

/**
 * Service for calculating different data about rides
 * @see com.yermilov.command.RideCommand
 * @see com.yermilov.command.RideStatisticsCommand
 */
public class RideStatisticsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideStatisticsService.class);
    private final static RideStatisticsService RIDE_STATISTICS_SERVICE = new RideStatisticsService();
    private IDAOFactory daoFactory;
    private RideStatisticsService(){
        daoFactory=DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static RideStatisticsService getRideStatisticsService(){
        return RIDE_STATISTICS_SERVICE;
    }

    /**
     *
     * @param client Client entity to calculate money it spent
     * @return Money the client spent
     * @throws DAOException Re-throws exception from RideDAO
     * @see RideDAO#getMoneySpentForClient(Client)
     */
    public double getClientsSpentMoney(Client client) throws DAOException {
        RideDAO rideDAO = daoFactory.getRideDAO();
        double moneySpent = rideDAO.getMoneySpentForClient(client);
        return moneySpent;
    }

    /**
     *
     * @param client Client
     * @param driver Driver
     * @param taxi Taxi
     * @param cost Cost
     * @param distance Distance
     * @param rideStart Time when Ride started
     * @param rideFinish Time when Ride finished
     * @return true if recorded successfully
     * @throws DAOException Re-throws DAOException from putRideToDatabase
     * @see #putRideToDatabase(Ride)
     */
    public boolean putRideToDatabase(Client client, Driver driver, Taxi taxi, double cost, double distance,
                                       Date rideStart, Date rideFinish) throws DAOException{
        Ride ride = new Ride(driver.getDriverId(),client.getClientId(),taxi.getTaxiId(),cost,distance,rideStart,rideFinish);
        return putRideToDatabase(ride);
    }

    /**
     *
     * @param ride Ride to put to database
     * @return true if created record successfully
     * @throws DAOException Re-throws DAOException from RideDAO
     * @see RideDAO#create(Ride)
     * @see #putRideToDatabase(Client, Driver, Taxi, double, double, Date, Date)
     */
    public boolean putRideToDatabase(Ride ride)throws DAOException{
        RideDAO rideDAO = daoFactory.getRideDAO();
        return rideDAO.create(ride);
    }

    /**
     *
     * @param client Client
     * @param from Number of rides to skip
     * @param limit Number of rides to display
     * @return Specific number of client's rides as java.util.List
     * @throws DAOException Re-throws DAOException from RideDAO
     * @see RideDAO#findRidesForClient(Client, int, int)
     */
    public List<Ride> getClientsRides(Client client, int from, int limit) throws DAOException {
        RideDAO rideDAO = daoFactory.getRideDAO();
        List<Ride> clientRides = rideDAO.findRidesForClient(client,from,limit);
        return clientRides;
    }

    /**
     *
     * @param client Client to show statistics for
     * @return Total number of rides for specific client
     * @throws DAOException Re-throws DAOException from RideDAO
     * @see RideDAO#findSize(Client)
     */
    public int getTableSize(Client client) throws DAOException {
        RideDAO rideDAO = daoFactory.getRideDAO();
        return rideDAO.findSize(client);
    }
}
