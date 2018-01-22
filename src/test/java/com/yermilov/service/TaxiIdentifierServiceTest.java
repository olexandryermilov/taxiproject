package com.yermilov.service;

import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.TaxiIdentifierService;
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

public class TaxiIdentifierServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }

    @After
    public void cleanTables() throws SQLException {
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }
    @Test
    public void getTaxi_ReturnsTaxi_WhenRightCarNumber() throws SQLException, DAOException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> taxiList = TableCreator.initTaxiTable();
        assertEquals(taxiList.get(0), TaxiIdentifierService.getTaxiIdentifierService().getTaxi(taxiList.get(0).getCarNumber()));
    }
    @Test
    public void getTaxi_ReturnsNull_WhenBadCarNumber() throws SQLException, DAOException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> taxiList = TableCreator.initTaxiTable();
        assertNull(TaxiIdentifierService.getTaxiIdentifierService().getTaxi(taxiList.get(0).getCarNumber()+"1"));
    }

    /*public Driver getDriver(Taxi taxi) throws DAOException{
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        return driverDAO.findById(taxi.getDriverId());
    }
    */
    @Test
    public void getDriver_ReturnsDriver_WhenRightTaxi() throws SQLException, DAOException {
        TableCreator.initUserTable();
        List<Driver> driverList = TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> taxiList = TableCreator.initTaxiTable();
        assertEquals(driverList.get(0), TaxiIdentifierService.getTaxiIdentifierService().getDriver(taxiList.get(0)));
    }
    @Test
    public void getTaxi_ReturnsTaxi_WhenRightTaxiId() throws SQLException, DAOException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> taxiList = TableCreator.initTaxiTable();
        assertEquals(taxiList.get(0), TaxiIdentifierService.getTaxiIdentifierService().getTaxi(taxiList.get(0).getTaxiId()));
    }
    @Test
    public void getTaxi_ReturnsNull_WhenBadTaxiId() throws SQLException, DAOException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        List<Taxi> taxiList = TableCreator.initTaxiTable();
        assertNull(TaxiIdentifierService.getTaxiIdentifierService().getTaxi(taxiList.get(0).getTaxiId()+10));
    }
}
