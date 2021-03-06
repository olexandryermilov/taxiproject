package com.yermilov.service;

import com.yermilov.domain.Taxi;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.GetCarService;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GetCarServiceTest {

    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }

    @Test
    public void getCar_ReturnsTaxiObject_WithSpecifiedTaxitype() throws SQLException, DAOException {
        TableCreator.initUserTable();
        TableCreator.initDriverTable();
        List<TaxiType> taxiTypes = TableCreator.initTaxiTypeTable();
        List<Taxi> taxies = TableCreator.initTaxiTable();
        Taxi taxi= GetCarService.getGetCarService().getCar(taxiTypes.get(0));
        assertTrue(taxies.contains(taxi));
        assertTrue(taxi.getTaxiTypeId()>=taxiTypes.get(0).getTaxiTypeId());
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }

}
