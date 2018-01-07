package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;

public class LoginService {
    private final static LoginService loginService = new LoginService();
    private LoginService(){

    }
    public static LoginService getLoginService(){
        return loginService;
    }

    public boolean verify(String email, String password) throws DAOException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        User user = userDAO.findByEmail(email);
        return user!=null&&password.equals(user.getPassword());
    }
}
