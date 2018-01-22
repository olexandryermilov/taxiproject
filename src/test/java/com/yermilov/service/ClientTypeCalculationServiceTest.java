package com.yermilov.service;

import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.ClientTypeCalculationService;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientTypeCalculationServiceTest {


    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void getClientsDiscount_FirstClientTest() throws SQLException, DAOException {
        TableCreator.initUserTable();
        List<Client> clientList = TableCreator.initClientTable();
        TableCreator.initClientTypeTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        TableCreator.initTaxiTable();
        TableCreator.initRideTable();
        assertEquals(0, ClientTypeCalculationService.getCostCalculationService().getClientsDiscount(clientList.get(0)));
        TableCleaner.cleanRideTable();
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanClientTypeTable();
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();
    }
    @Test
    public void getClientsDiscount_SecondClientTest() throws SQLException, DAOException {
        TableCreator.initUserTable();
        List<Client> clientList = TableCreator.initClientTable();
        TableCreator.initClientTypeTable();
        TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        TableCreator.initTaxiTable();
        TableCreator.initRideTable();
        assertEquals(3, ClientTypeCalculationService.getCostCalculationService().getClientsDiscount(clientList.get(1)));
        TableCleaner.cleanRideTable();
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanClientTypeTable();
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();

    }
}
