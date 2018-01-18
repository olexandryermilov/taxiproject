package com.yermilov.admin.service;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiTypeDAO;
import com.yermilov.domain.ClientType;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class AddClientTypeService {
    private final static Logger logger = LoggerFactory.getLogger(AddClientTypeService.class);
    private final static AddClientTypeService ADD_CLIENT_TYPE_SERVICE = new AddClientTypeService();
    private IDAOFactory daoFactory;
    private AddClientTypeService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static AddClientTypeService getAddClientTypeService(){
        return ADD_CLIENT_TYPE_SERVICE;
    }

    public boolean addClientType(ClientType clientType)throws DAOException{
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        clientTypeDAO.create(clientType);
        return true;
    }

}
