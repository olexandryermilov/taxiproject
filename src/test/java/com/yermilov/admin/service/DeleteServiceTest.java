package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DeleteServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }

    @Test
    public void deleteUser_DeletesUserClientAndDriver() throws SQLException, DAOException {
        List<User> userList= TableCreator.initUserTable();
        List<Client> clientList = TableCreator.initClientTable();
        List<Driver> driverList = TableCreator.initDriverTable();
        int idToDelete = userList.get(0).getUserId();
        DeleteService.getDeleteService().delete(idToDelete);
        userList.remove(0);
        Client client = clientList.remove(0);
        Driver driver = driverList.remove(0);
        DAOFactory daoFactory = DAOFactory.getInstance();
        assertTrue(userList.equals(daoFactory.getUserDAO().findLimitedAmount(0,userList.size()))&&
                    daoFactory.getClientDAO().findClientByUserId(idToDelete)==null&&
                    daoFactory.getDriverDAO().findByUserId(idToDelete)==null);
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();
    }
}
