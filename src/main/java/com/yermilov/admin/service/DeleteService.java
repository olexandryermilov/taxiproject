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

/**
 *  Service for deleting specific user record from database and related to it Client and Driver records
 *  @see com.yermilov.admin.command.DeleteCommand
 */
public class DeleteService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteService.class);
    private final static DeleteService DELETE_SERVICE = new DeleteService();
    private IDAOFactory daoFactory;
    private DeleteService(){
        daoFactory= DAOFactory.getInstance();
    }
    /**
     *
     * @return Instance of this class
     */
    public static DeleteService getDeleteService(){
        return DELETE_SERVICE;
    }

    /**
     * Deletes user and related to him client and driver entities from database
     * @param idToDelete Id of user that admin tried to delete
     * @return true if deletes successfully
     * @throws DAOException Re-throws DAOException from UserDAO, ClientDAO or DriverDAO methods
     * @see UserDAO#delete(int)
     * @see ClientDAO#delete(Client)
     * @see DriverDAO#delete(Driver)
     */
    public boolean delete(int idToDelete) throws DAOException {
        UserDAO userDAO = daoFactory.getUserDAO();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        DriverDAO driverDAO = daoFactory.getDriverDAO();
        try {
            TransactionManager.beginTransaction();
            clientDAO.delete(new Client(idToDelete));
            Driver driver = driverDAO.findByUserId(idToDelete);
            if (driver != null) {
                driverDAO.delete(driver);
            }
            userDAO.delete(idToDelete);
            TransactionManager.endTransaction();
        } catch (TransactionException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return true;
    }
}
