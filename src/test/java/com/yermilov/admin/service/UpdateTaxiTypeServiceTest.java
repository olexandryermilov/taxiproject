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


public class UpdateTaxiTypeServiceTest {


    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void updateTaxiType_UpdatesTaxiType() throws DAOException, SQLException {
        List<TaxiType> taxiTypeList = TableCreator.initTaxiTypeTable();
        TaxiType taxiType = new TaxiType(3,"cab");
        taxiType.setTaxiTypeId(taxiTypeList.size());
        taxiTypeList.set(taxiTypeList.size()-1,taxiType);
        UpdateTaxiTypeService.getTaxiTypeService().updateTaxiType(taxiType);
        assertEquals(taxiTypeList,DAOFactory.getInstance().getTaxiTypeDAO().findAll());
        TableCleaner.cleanTaxiTypeTable();
    }
}
