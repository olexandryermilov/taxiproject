package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.AddCarException;
import com.yermilov.exceptions.DAOException;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddCarServiceTest {

    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void findDriverByUserId_ReturnsCorrectDriver() throws DAOException,SQLException{
        TableCreator.initUserTable();
        List<Driver> allDrivers = TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        TableCreator.initTaxiTable();
        Driver driver = allDrivers.get(0);
        assertEquals(driver,AddCarService.getAddCarService().findDriverByUserId(driver.getUserId()));
    }
    @Test
    public void findDriverByUserId_ReturnsNull_WhenThereIsNoSuchDriver() throws DAOException,SQLException{
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        TableCreator.initTaxiTable();
        assertNull(AddCarService.getAddCarService().findDriverByUserId(100));
    }

    @Test
    public void addCar_AddsCar_WhenThereIsNoSuchCar() throws DAOException, SQLException, AddCarException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> allTaxies = TableCreator.initTaxiTable();
        Taxi taxi = new Taxi(1,1,"AA1111AA");
        taxi.setTaxiId(allTaxies.size()+1);
        allTaxies.add(taxi);
        AddCarService.getAddCarService().addCar(taxi);
        assertEquals(taxi,DAOFactory.getInstance().getTaxiDAO().findByCarNumber(taxi.getCarNumber()));
    }
    @Test(expected = AddCarException.class)
    public void addCar_ThrowsException_WhenThereExistsThisCar() throws DAOException, SQLException, AddCarException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> allTaxies = TableCreator.initTaxiTable();
        Taxi taxi = new Taxi(1,1,allTaxies.get(0).getCarNumber());
        taxi.setTaxiId(allTaxies.size()+1);
        AddCarService.getAddCarService().addCar(taxi);

    }
    @After
    public void cleanTables() throws SQLException {
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }
}
