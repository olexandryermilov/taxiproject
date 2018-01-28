package com.yermilov.admin.service;

import com.yermilov.domain.ClientType;
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


public class ClientTypesServiceTest {

    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void getAllClientTypes_ReturnsAllClientType() throws DAOException,SQLException {
        List<ClientType> allClientTypes= TableCreator.initClientTypeTable();
        assertEquals(allClientTypes.subList(1,allClientTypes.size()),ClientTypesService.getClientTypesService().getAllClientTypes());
        TableCleaner.cleanClientTypeTable();
    }
}
