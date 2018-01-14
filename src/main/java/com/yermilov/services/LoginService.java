package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private final static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final static LoginService loginService = new LoginService();
    private IDAOFactory daoFactory;
    private LoginService(){
        daoFactory=DAOFactory.getInstance();
    }
    public static LoginService getLoginService(){
        return loginService;
    }

    public User getUser(String email, String password) throws DAOException {
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.findByEmail(email);
        logger.info("User "+email+" tried to login.");
        return (password.equals(user.getPassword()))?user:null;
    }
    public void setDaoFactory(IDAOFactory daoFactory){
        this.daoFactory=daoFactory;
    }
}
