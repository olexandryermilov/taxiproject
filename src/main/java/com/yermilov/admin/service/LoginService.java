package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.AdminDAO;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private final static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final static LoginService loginService = new LoginService();
    private LoginService(){

    }
    public static LoginService getAdminLoginService(){
        return loginService;
    }

    public Admin getAdmin(String email, String password) throws DAOException {
        AdminDAO adminDAO = DAOFactory.getAdminDAO();
        Admin admin = adminDAO.findByEmail(email);
        logger.info(email+" tried to login into admin.");
        return (password.equals(admin.getPassword()))?admin:null;
        //return admin!=null&&password.equals(admin.getPassword());
    }
}
