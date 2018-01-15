package com.yermilov.admin.service;

import com.yermilov.dao.*;
import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.DriverRegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class RegisterDriverService {
    private final static Logger logger = LoggerFactory.getLogger(RegisterDriverService.class);
    private final static RegisterDriverService REGISTER_DRIVER_SERVICE = new RegisterDriverService();
    private IDAOFactory daoFactory;
    private RegisterDriverService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static RegisterDriverService getRegisterDriverService(){
        return REGISTER_DRIVER_SERVICE;
    }

    public boolean registerDriver(int userId) throws DAOException, SQLException, TransactionException, DriverRegistrationException {
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        if(driverDAO.findByUserId(userId)!=null){
            throw new DriverRegistrationException("User is already a driver");
        }
        return driverDAO.create(new Driver(userId));
    }
}
