package com.yermilov.service;

import com.yermilov.admin.service.TaxiTypesService;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.RideOrderService;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RideOrderServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }

    @Test
    public void findAll_ReturnsAllTaxiTypes() throws SQLException, DAOException {
        List<TaxiType> taxiTypes= TableCreator.initTaxiTypeTable();
        assertEquals(taxiTypes, RideOrderService.getRideOrderService().getAllTaxiTypes());
        TableCleaner.cleanTaxiTypeTable();
    }
}
