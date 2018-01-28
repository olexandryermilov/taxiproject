package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiTypeDAO;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for updating specific TaxiType record in database
 * @see com.yermilov.admin.command.UpdateTaxiTypeCommand
 */
public class UpdateTaxiTypeService {
    private final static Logger logger = LoggerFactory.getLogger(UpdateTaxiTypeService.class);
    private final static UpdateTaxiTypeService TAXI_TYPE_SERVICE = new UpdateTaxiTypeService();
    private IDAOFactory daoFactory;
    private UpdateTaxiTypeService(){
        daoFactory= DAOFactory.getInstance();
    }
    /**
     *
     * @return Instance of this class
     */
    public static UpdateTaxiTypeService getTaxiTypeService(){
        return TAXI_TYPE_SERVICE;
    }

    /**
     *
     * @param entity Specific TaxiType to update
     * @return true if updated successfully
     * @throws DAOException Re-throws DAOException from TaxiTypeDAO
     * @see TaxiTypeDAO#update(TaxiType)
     */
    public boolean updateTaxiType(TaxiType entity) throws DAOException {
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        taxiTypeDAO.update(entity);
        return true;
    }
}
