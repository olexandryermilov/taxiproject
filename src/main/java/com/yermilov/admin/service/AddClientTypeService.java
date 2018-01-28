package com.yermilov.admin.service;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.ClientType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for adding ClientType to database
 * @see com.yermilov.admin.command.AddClientTypeCommand
 */
public class AddClientTypeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddClientTypeService.class);
    private final static AddClientTypeService ADD_CLIENT_TYPE_SERVICE = new AddClientTypeService();
    private IDAOFactory daoFactory;
    private AddClientTypeService(){
        daoFactory= DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static AddClientTypeService getAddClientTypeService(){
        return ADD_CLIENT_TYPE_SERVICE;
    }

    /**
     *
     * @param clientType ClientType to add
     * @return true if successfully added clientType to database
     * @throws DAOException Re-throws DAOException from ClientTypeDAO method
     * @see ClientTypeDAO#create(ClientType)
     */
    public boolean addClientType(ClientType clientType)throws DAOException{
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        clientTypeDAO.create(clientType);
        return true;
    }

}
