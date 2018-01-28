package com.yermilov.admin.service;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.ClientType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for changing specific ClientType record in database
 * @see com.yermilov.admin.command.UpdateClientTypeCommand
 */
public class UpdateClientTypeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateClientTypeService.class);
    private final static UpdateClientTypeService UPDATE_CLIENT_TYPE_SERVICE = new UpdateClientTypeService();
    private IDAOFactory daoFactory;
    private UpdateClientTypeService(){
        daoFactory= DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static UpdateClientTypeService getUpdateClientTypeService(){
        return UPDATE_CLIENT_TYPE_SERVICE;
    }

    /**
     *
     * @param entity Specific ClientType to update
     * @return true if updated successfully
     * @throws DAOException Re-throws DAOException from ClientTypeDAO
     * @see ClientTypeDAO#update(ClientType)
     */
    public boolean updateClientType(ClientType entity) throws DAOException {
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        clientTypeDAO.update(entity);
        return true;
    }
}
