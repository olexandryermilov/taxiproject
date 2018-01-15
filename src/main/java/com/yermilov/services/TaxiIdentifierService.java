package com.yermilov.services;

import com.yermilov.dao.*;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaxiIdentifierService {
    private final static Logger logger = LoggerFactory.getLogger(TaxiIdentifierService.class);
    private final static TaxiIdentifierService TAXI_IDENTIFIER_SERVICE = new TaxiIdentifierService();
    private IDAOFactory daoFactory;
    private TaxiIdentifierService(){
        daoFactory=DAOFactory.getInstance();
    }
    public static TaxiIdentifierService getTaxiIdentifierService(){
        return TAXI_IDENTIFIER_SERVICE;
    }

    public Taxi getTaxi(String carNumber) throws DAOException {
        TaxiDAO taxiDAO = daoFactory.getTaxiDAO();
        return taxiDAO.findByCarNumber(carNumber);
    }
    public Driver getDriver(Taxi taxi) throws DAOException{
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        return driverDAO.findById(taxi.getDriverId());
    }
    public Taxi getTaxi(int taxiId) throws DAOException{
        TaxiDAO taxiDAO=daoFactory.getTaxiDAO();
        return taxiDAO.findById(taxiId);
    }
}
