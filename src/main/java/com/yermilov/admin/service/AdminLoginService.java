package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.AdminDAO;
import com.yermilov.domain.Admin;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminLoginService {
    private final static Logger logger = LoggerFactory.getLogger(AdminLoginService.class);
    private final static AdminLoginService loginService = new AdminLoginService();
    private AdminLoginService(){

    }
    public static AdminLoginService getAdminLoginService(){
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
