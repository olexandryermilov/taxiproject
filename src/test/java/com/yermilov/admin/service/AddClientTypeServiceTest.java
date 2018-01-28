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

public class AddClientTypeServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void addsClientType() throws DAOException,SQLException {
        List<ClientType> allClientTypes= TableCreator.initClientTypeTable();
        ClientType clientType = new ClientType(30,"best",50000.0);
        clientType.setClientTypeId(allClientTypes.size()+1);
        AddClientTypeService.getAddClientTypeService().addClientType(clientType);
        allClientTypes.add(clientType);
        assertEquals(allClientTypes.subList(1,allClientTypes.size()),DAOFactory.getInstance().getClientTypeDAO().findAll());
        TableCleaner.cleanClientTypeTable();
    }

}
