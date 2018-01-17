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

public class UpdateClientTypeService {
    private final static Logger logger = LoggerFactory.getLogger(UpdateClientTypeService.class);
    private final static UpdateClientTypeService UPDATE_CLIENT_TYPE_SERVICE = new UpdateClientTypeService();
    private IDAOFactory daoFactory;
    private UpdateClientTypeService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static UpdateClientTypeService getUpdateClientTypeService(){
        return UPDATE_CLIENT_TYPE_SERVICE;
    }

    public boolean updateClientType(ClientType entity) throws DAOException {
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        clientTypeDAO.update(entity);
        return true;
    }
}
