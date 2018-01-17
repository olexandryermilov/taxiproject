package com.yermilov.admin.service;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiTypeDAO;
import com.yermilov.domain.ClientType;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientTypesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientTypesService.class);
    private final static ClientTypesService CLIENT_TYPES_SERVICE = new ClientTypesService();
    private IDAOFactory daoFactory = DAOFactory.getInstance();
    private ClientTypesService(){
        daoFactory = DAOFactory.getInstance();
    }
    public static ClientTypesService getClientTypesService(){
        return CLIENT_TYPES_SERVICE;
    }
    public List<ClientType> getAllClientTypes() throws DAOException{
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        List<ClientType> clientTypes = clientTypeDAO.findAll();
        return clientTypes;
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
