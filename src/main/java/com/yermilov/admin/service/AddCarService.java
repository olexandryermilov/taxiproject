package com.yermilov.admin.service;

import com.yermilov.dao.*;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class AddCarService {
    private final static Logger logger = LoggerFactory.getLogger(AddCarService.class);
    private final static AddCarService ADD_CAR_SERVICE = new AddCarService();
    private IDAOFactory daoFactory;
    private AddCarService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static AddCarService getAddCarService(){
        return ADD_CAR_SERVICE;
    }

    public Driver findDriverByUserId(int userId) throws DAOException, SQLException, TransactionException{
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        Driver driver = driverDAO.findByUserId(userId);
        return driver;
    }
    public boolean addCar(Taxi taxi)throws DAOException, SQLException, TransactionException{
        TaxiDAO taxiDAO = daoFactory.getTaxiDAO();
        taxiDAO.create(taxi);
        return true;
    }

}
