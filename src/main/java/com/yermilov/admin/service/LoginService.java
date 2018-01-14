package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.AdminDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private final static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final static LoginService loginService = new LoginService();
    private IDAOFactory daoFactory;
    private LoginService(){
        daoFactory = DAOFactory.getInstance();
    }
    public static LoginService getLoginService(){
        return loginService;
    }

    public Admin getAdmin(String email, String password) throws DAOException {
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        Admin admin = adminDAO.findByEmail(email);
        logger.info(email+" tried to login into admin.");
        return (password.equals(admin.getPassword()))?admin:null;
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
