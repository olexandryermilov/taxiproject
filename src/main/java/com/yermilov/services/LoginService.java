package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;

public class LoginService {
    private final static LoginService loginService = new LoginService();
    private LoginService(){

    }
    public static LoginService getLoginService(){
        return loginService;
    }

    public boolean verify(String login, String password){
        UserDAO userDAO = DAOFactory.getUserDAO();
        User user = userDAO.findByEmail(login);
        return user!=null&&password.equals(user.getPassword());
    }
}
