package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.DriverDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Driver;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.DriverRegistrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Service for registering user as driver
 *  @see com.yermilov.admin.command.RegisterDriverCommand
 */
public class RegisterDriverService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterDriverService.class);
    private final static RegisterDriverService REGISTER_DRIVER_SERVICE = new RegisterDriverService();
    private IDAOFactory daoFactory;
    private RegisterDriverService(){
        daoFactory= DAOFactory.getInstance();
    }
    /**
     *
     * @return Instance of this class
     */
    public static RegisterDriverService getRegisterDriverService(){
        return REGISTER_DRIVER_SERVICE;
    }

    /**
     *
     * @param userId id of specific user which was registered as driver
     * @return true if registration is successful
     * @throws DAOException Re-throws DAOException from DriverDAO
     * @throws DriverRegistrationException if this user is already a driver
     * @see DriverDAO#create(Driver)
     * @see DriverDAO#findByUserId(int)
     */
    public boolean registerDriver(int userId) throws DAOException, DriverRegistrationException {
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        if(driverDAO.findByUserId(userId)!=null){
            throw new DriverRegistrationException("User is already a driver");
        }
        return driverDAO.create(new Driver(userId));
    }
}
