package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.DriverDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Driver;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.DriverRegistrationException;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RegisterDriverServiceTest {
    /*public boolean registerDriver(int userId) throws DAOException, DriverRegistrationException {
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        if(driverDAO.findByUserId(userId)!=null){
            throw new DriverRegistrationException("User is already a driver");
        }
        return driverDAO.create(new Driver(userId));
    }
    */
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void registerDriver_RegistersNewDriver() throws SQLException, DriverRegistrationException, DAOException {
        List<User> userList = TableCreator.initUserTable();
        List<Driver> driverList = TableCreator.initDriverTable();
        Driver driver = new Driver(userList.get(3).getUserId());
        driver.setDriverId(driverList.size()+1);
        RegisterDriverService.getRegisterDriverService().registerDriver(driver.getUserId());
        assertEquals(driver,DAOFactory.getInstance().getDriverDAO().findByUserId(driver.getUserId()));
    }
    @Test(expected = DriverRegistrationException.class)
    public void registerDriver_ThrowsExceptionIfUserIsADriver() throws SQLException, DriverRegistrationException, DAOException {
        List<User> userList = TableCreator.initUserTable();
        List<Driver> driverList = TableCreator.initDriverTable();
        Driver driver = new Driver(userList.get(0).getUserId());
        driver.setDriverId(driverList.size()+1);
        RegisterDriverService.getRegisterDriverService().registerDriver(driver.getUserId());

    }
    @After
    public void cleanTables() throws SQLException {
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }
}
