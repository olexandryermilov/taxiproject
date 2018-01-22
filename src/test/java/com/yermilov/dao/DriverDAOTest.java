package com.yermilov.dao;

import com.yermilov.TableCreator;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Driver;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DriverDAOTest {
    private static List<Driver> allDrivers;
    private static List<User> allUsers;

    @Before
    public void initDatabase() throws SQLException {
        allUsers = TableCreator.initUserTable();
        allDrivers = TableCreator.initDriverTable();
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @After
    public void dropTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_DROP_DRIVER = "DROP TABLE `driver`";
        PreparedStatement ps = connection.prepareStatement(SQL_DROP_DRIVER);
        ps.execute();
        String SQL_DROP_USER = "DROP TABLE `user`";
        ps = connection.prepareStatement(SQL_DROP_USER);
        ps.execute();
    }

    @Test
    public void findByUserId_ReturnsCorrectDriver_WhenCorrectUserId() throws DAOException {
        final int USER_ID=1;
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        assertEquals(allDrivers.get(0),driverDAO.findByUserId(USER_ID));
    }
    @Test
    public void findByUserId_ReturnsNull_WhenWrongUserId() throws DAOException {
        final int USER_ID=15;
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        assertNull(driverDAO.findByUserId(USER_ID));
    }
    @Test
    public void delete_DeletesFromDatabase_WhenCorrectData() throws DAOException {
        final Driver DRIVER_TO_DELETE = allDrivers.get(0);
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        driverDAO.delete(DRIVER_TO_DELETE);
        assertNull(driverDAO.findByUserId(DRIVER_TO_DELETE.getUserId()));
    }

    @Test
    public void create_CreatesNewRecord_WhenCorrectData() throws DAOException{
        final Driver DRIVER_TO_ADD = new Driver(4);
        DRIVER_TO_ADD.setDriverId(4);
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        driverDAO.create(DRIVER_TO_ADD);
        assertEquals(DRIVER_TO_ADD,driverDAO.findByUserId(4));
    }
    @Test
    public void findById_ReturnsCorrectDriver_WhenCorrectId() throws DAOException {
        final int DRIVER_ID=1;
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        assertEquals(allDrivers.get(0),driverDAO.findById(DRIVER_ID));
    }
    @Test
    public void findById_ReturnsNull_WhenWrongId() throws DAOException {
        final int DRIVER_ID=15;
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();
        assertNull(driverDAO.findById(DRIVER_ID));
    }
}
