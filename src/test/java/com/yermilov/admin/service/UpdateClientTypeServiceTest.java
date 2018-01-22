package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
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


public class UpdateClientTypeServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void updateClientType_UpdatesClientType() throws DAOException, SQLException {
        List<ClientType> clientTypeList = TableCreator.initClientTypeTable();
        ClientType clientType = new ClientType(30,"admin",40000);
        clientType.setClientTypeId(clientTypeList.size());
        clientTypeList.set(clientTypeList.size()-1,clientType);
        UpdateClientTypeService.getUpdateClientTypeService().updateClientType(clientType);
        assertEquals(clientTypeList.subList(1,clientTypeList.size()),DAOFactory.getInstance().getClientTypeDAO().findAll());
        TableCleaner.cleanClientTypeTable();
    }
}
