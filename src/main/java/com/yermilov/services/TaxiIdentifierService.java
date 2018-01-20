package com.yermilov.services;

import com.yermilov.dao.*;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for getting Taxi from it's car number or id and determining it's driver
 * @see com.yermilov.command.RideCommand
 * @see com.yermilov.command.RideStatisticsCommand
 * @see com.yermilov.command.CostCalculationCommand
 */
public class TaxiIdentifierService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaxiIdentifierService.class);
    private final static TaxiIdentifierService TAXI_IDENTIFIER_SERVICE = new TaxiIdentifierService();
    private IDAOFactory daoFactory;
    private TaxiIdentifierService(){
        daoFactory=DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static TaxiIdentifierService getTaxiIdentifierService(){
        return TAXI_IDENTIFIER_SERVICE;
    }

    /**
     *
     * @param carNumber Car number of taxi
     * @return Taxi object if there is a taxi ith such car number, null otherwise
     * @throws DAOException Re-throws DAOExceptions from TaxiDAO
     * @see TaxiDAO#findByCarNumber(String)
     */
    public Taxi getTaxi(String carNumber) throws DAOException {
        TaxiDAO taxiDAO = daoFactory.getTaxiDAO();
        return taxiDAO.findByCarNumber(carNumber);
    }

    /**
     *
     * @param taxi Taxi to find it's driver
     * @return Driver if there is a Driver with such id or null otherwise
     * @throws DAOException Re-throws DAOException from DriverDAO
     * @see DriverDAO#findById(int)
     */
    public Driver getDriver(Taxi taxi) throws DAOException{
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        return driverDAO.findById(taxi.getDriverId());
    }

    /**
     *
     * @param taxiId Id of taxi
     * @return Taxi if there is a Taxi with such id or null otherwise
     * @throws DAOException Re-throws DAOException from TaxiDAO
     */
    public Taxi getTaxi(int taxiId) throws DAOException{
        TaxiDAO taxiDAO=daoFactory.getTaxiDAO();
        return taxiDAO.findById(taxiId);
    }
}
