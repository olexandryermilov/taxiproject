package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private final static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final static LoginService loginService = new LoginService();
    private LoginService(){

    }
    public static LoginService getLoginService(){
        return loginService;
    }

    public User getUser(String email, String password) throws DAOException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        User user = userDAO.findByEmail(email);
        logger.info("User "+email+" tried to login.");
        return (password.equals(user.getPassword()))?user:null;
        //return user!=null&&password.equals(user.getPassword());
    }
}
