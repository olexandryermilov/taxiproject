package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.AdminDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for authorization into admin interface
 * @see com.yermilov.admin.command.LoginCommand
 */
public class LoginService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final static LoginService loginService = new LoginService();
    private IDAOFactory daoFactory;
    private LoginService(){
        daoFactory = DAOFactory.getInstance();
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
     * @param email Email
     * @param password Password (not encrypted)
     * @return Admin object if there is an admin in database with such email and password, null otherwise
     * @throws DAOException Re-throws DAOException from AdminDAO
     * @see AdminDAO#findByEmail(String)
     */
    public Admin getAdmin(String email, String password) throws DAOException {
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        Admin admin = adminDAO.findByEmail(email);
        LOGGER.info(email+" tried to login into admin.");
        return (password.equals(admin.getPassword()))?admin:null;
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
