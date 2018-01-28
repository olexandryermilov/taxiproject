package com.yermilov.admin.service;

import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TaxiTypesServiceTest {

    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void getAllTaxiTypes_ReturnsAllTaxiTypes() throws DAOException,SQLException {
        List<TaxiType> taxiTypes= TableCreator.initTaxiTypeTable();
        assertEquals(taxiTypes, TaxiTypesService.getTaxiTypesService().getAllTaxiTypes());
        TableCleaner.cleanTaxiTypeTable();
    }
}
