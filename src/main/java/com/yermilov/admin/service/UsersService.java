package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UsersService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UsersService.class);
    private final static UsersService usersService = new UsersService();
    private IDAOFactory daoFactory = DAOFactory.getInstance();
    private UsersService(){
        daoFactory = DAOFactory.getInstance();
    }
    public static UsersService getUsersService(){
        return usersService;
    }
    public List<User> getAllUsers() throws DAOException{
        UserDAO userDAO = daoFactory.getUserDAO();
        List<User> allUsers = userDAO.findAll();
        return allUsers;
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
