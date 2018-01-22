package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.DriverDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiDAO;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.AddCarException;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for adding car of specific driver to database
 * @see com.yermilov.admin.command.AddCarCommand
 */
public class AddCarService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddCarService.class);
    private final static AddCarService ADD_CAR_SERVICE = new AddCarService();
    private IDAOFactory daoFactory;
    private AddCarService(){
        daoFactory= DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static AddCarService getAddCarService(){
        return ADD_CAR_SERVICE;
    }

    /**
     *
     * @param userId Id of user to find driver with the same userid
     * @return Driver if there is a driver with such userid or null if there isn't
     * @throws DAOException Re-throws DAOException from DriverDAO method
     * @see DriverDAO
     */
    public Driver findDriverByUserId(int userId) throws DAOException{
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        Driver driver = driverDAO.findByUserId(userId);
        return driver;
    }

    /**
     *
     * @param taxi Taxi to add to database
     * @return true if created taxi successfully
     * @throws DAOException Re-throws DAOException from TaxiDAO method
     * @throws AddCarException If there is already a car with such carNumber
     * @see TaxiDAO#create(Taxi)
     */
    public boolean addCar(Taxi taxi) throws DAOException, AddCarException {
        TaxiDAO taxiDAO = daoFactory.getTaxiDAO();
        if(taxiDAO.findByCarNumber(taxi.getCarNumber())==null){
            taxiDAO.create(taxi);
        }
        else{
            throw new AddCarException("There is already a car with such car number");
        }
        return true;
    }

}
