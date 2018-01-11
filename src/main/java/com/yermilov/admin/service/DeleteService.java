package com.yermilov.admin.service;

import com.yermilov.dao.AdminDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DeleteService {
    private final static Logger logger = LoggerFactory.getLogger(DeleteService.class);
    private final static DeleteService DELETE_SERVICE = new DeleteService();
    private DeleteService(){

    }
    public static DeleteService getDeleteService(){
        return DELETE_SERVICE;
    }

    public boolean delete(List<Integer> idToDelete) throws DAOException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        for(Integer i : idToDelete){
            userDAO.delete(i);
        }
        return true;
    }
}
