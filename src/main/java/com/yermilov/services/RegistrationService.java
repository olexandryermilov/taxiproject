package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.RegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.transactions.TransactionManager;

import java.sql.SQLException;

public class RegistrationService {
    private final static RegistrationService registrationService = new RegistrationService();
    private RegistrationService(){
    }
    public static RegistrationService getRegistrationService(){
        return registrationService;
    }
    public void register(String login, String password, String name, String surname) throws RegistrationException, SQLException, TransactionException, DAOException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        TransactionManager.beginTransaction();
        if(userDAO.findByEmail(login)==null){
            User user = new User(login,password,name,surname);
            try{
                userDAO.create(user);
            }
            catch(DAOException e){
                //todo: log
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
