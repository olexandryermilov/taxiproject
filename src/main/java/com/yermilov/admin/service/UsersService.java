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
    public int getTableSize() throws DAOException {
        UserDAO userDAO = daoFactory.getUserDAO();
        return userDAO.findSize();
    }
    public List<User> getUsers(int from, int limit) throws DAOException{
        UserDAO userDAO = daoFactory.getUserDAO();
        List<User> users = userDAO.findLimitedAmount(from,limit);
        return users;
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
