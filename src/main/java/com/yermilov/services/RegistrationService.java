package com.yermilov.services;


import com.yermilov.dao.ClientDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.Client;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.RegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class RegistrationService {
    private final static Logger logger = LoggerFactory.getLogger(RegistrationService.class);
    private final static RegistrationService registrationService = new RegistrationService();
    private IDAOFactory daoFactory;
    private RegistrationService(){
        daoFactory=DAOFactory.getInstance();
    }
    public static RegistrationService getRegistrationService(){
        return registrationService;
    }
    public boolean register(String email, String password, String name, String surname) throws RegistrationException, SQLException, TransactionException, DAOException {
        UserDAO userDAO = daoFactory.getUserDAO();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        TransactionManager.beginTransaction();
        if(userDAO.findByEmail(email)==null){
            User user = new User(email,password,name,surname);
            try{
                if(!userDAO.create(user))throw new RegistrationException("DAO couldn't create a client");
                user = userDAO.findByEmail(email);
                logger.info("Created new user : {}",user);
                Client client = new Client(user.getUserId());
                if(!clientDAO.create(client))throw new RegistrationException("DAO couldn't create a client");
                logger.info("Created new client: {}",client);
                return true;
            }
            catch(DAOException e){
                logger.error(e.getMessage());
                throw new RegistrationException(e);
            }
            finally {
                TransactionManager.endTransaction();
            }
        }
        else{
            TransactionManager.endTransaction();
            throw new RegistrationException("This email is already occupied");
        }
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
