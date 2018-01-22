package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

/**
 * Service for authorization
 * @see com.yermilov.command.LoginCommand
 */
public class LoginService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final static LoginService loginService = new LoginService();
    private IDAOFactory daoFactory;
    private LoginService(){
        daoFactory=DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static LoginService getLoginService(){
        return loginService;
    }

    /**
     *
     * @param email Email that user entered
     * @param password Password that user entered (not encrypted)
     * @return User if there exists a user with such email and password, null otherwise
     * @throws DAOException Re-throws DAOException from UserDAO
     * @see UserDAO#findByEmail(String)
     */
    public User getUser(String email, String password) throws DAOException {
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.findByEmail(email);
        if(user==null){
            return null;
        }
        LOGGER.info("User "+email+" tried to login.");
        return (password.equals(user.getPassword()))?user:null;
    }
    public void setDaoFactory(IDAOFactory daoFactory){
        this.daoFactory=daoFactory;
    }
}
