package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.RegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.transactions.TransactionManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class RegistrationService {
    private final static Logger logger = LoggerFactory.getLogger(RegistrationService.class);
    {
        DOMConfigurator.configure("log4j.xml");
    }
    private final static RegistrationService registrationService = new RegistrationService();
    private RegistrationService(){
    }
    public static RegistrationService getRegistrationService(){
        return registrationService;
    }
    public void register(String email, String password, String name, String surname) throws RegistrationException, SQLException, TransactionException, DAOException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        TransactionManager.beginTransaction();
        if(userDAO.findByEmail(email)==null){
            User user = new User(email,password,name,surname);
            try{
                userDAO.create(user);
                logger.info("Created new user : {}",user);
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
}
