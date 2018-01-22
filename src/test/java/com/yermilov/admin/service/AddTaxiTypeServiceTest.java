package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
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

public class AddTaxiTypeServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void addTaxiType_AddsTaxiType() throws DAOException,SQLException {
        List<TaxiType> allTaxiTypes = TableCreator.initTaxiTypeTable();
        TaxiType taxiType = new TaxiType(3.0,"cab");
        taxiType.setTaxiTypeId(allTaxiTypes.size()+1);
        allTaxiTypes.add(taxiType);
        AddTaxiTypeService.getAddTaxiTypeService().addTaxiType(taxiType);
        assertEquals(allTaxiTypes,DAOFactory.getInstance().getTaxiTypeDAO().findAll());
        TableCleaner.cleanTaxiTypeTable();
    }
}
