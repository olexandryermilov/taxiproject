package com.yermilov.admin.service;

import com.yermilov.dao.*;
import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DeleteService {
    private final static Logger logger = LoggerFactory.getLogger(DeleteService.class);
    private final static DeleteService DELETE_SERVICE = new DeleteService();
    private IDAOFactory daoFactory;
    private DeleteService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static DeleteService getDeleteService(){
        return DELETE_SERVICE;
    }

    public boolean delete(List<Integer> idToDelete) throws DAOException, SQLException, TransactionException {

        UserDAO userDAO = daoFactory.getUserDAO();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        for(Integer i : idToDelete){
            TransactionManager.beginTransaction();
            clientDAO.delete(new Client(i));
            Driver driver = driverDAO.findByUserId(i);
            if(driver!=null){
                driverDAO.delete(driver);
            }
            userDAO.delete(i);
            TransactionManager.endTransaction();
        }

        return true;
    }
}
