package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.DriverDAO;
import com.yermilov.dao.TaxiDAO;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaxiIdentifierService {
    private final static Logger logger = LoggerFactory.getLogger(TaxiIdentifierService.class);
    private final static TaxiIdentifierService TAXI_IDENTIFIER_SERVICE = new TaxiIdentifierService();
    private TaxiIdentifierService(){

    }
    public static TaxiIdentifierService getTaxiIdentifierService(){
        return TAXI_IDENTIFIER_SERVICE;
    }

    public Taxi getTaxi(String carNumber) throws DAOException {
        TaxiDAO taxiDAO = DAOFactory.getTaxiDAO();
        Taxi taxi = new Taxi(1,1,carNumber);
        taxi.setTaxiId(1);
        return taxi;
        //return taxiDAO.findByCarNumber(carNumber);
    }
    public Driver getDriver(Taxi taxi) throws DAOException{
        DriverDAO driverDAO = DAOFactory.getDriverDAO();
        Driver driver = new Driver(1);
        driver.setDriverId(1);
        return driver;
        //return driverDAO.findById(taxi.getDriverId());
    }
}
