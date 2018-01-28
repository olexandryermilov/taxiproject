package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiTypeDAO;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Service for adding specific TaxiType to database
 *  @see com.yermilov.admin.command.AddTaxiTypeCommand
 */
public class AddTaxiTypeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddTaxiTypeService.class);
    private final static AddTaxiTypeService ADD_TAXI_TYPE_SERVICE = new AddTaxiTypeService();
    private IDAOFactory daoFactory;
    private AddTaxiTypeService(){
        daoFactory= DAOFactory.getInstance();
    }
    /**
     *
     * @return Instance of this class
     */
    public static AddTaxiTypeService getAddTaxiTypeService(){
        return ADD_TAXI_TYPE_SERVICE;
    }

    /**
     * @param taxiType TaxiType to add to database
     * @return true if successfully added taxiType to database
     * @throws DAOException Re-throws DAOException from TaxiTypeDAO method
     * @see TaxiTypeDAO#create(TaxiType)
     */
    public boolean addTaxiType(TaxiType taxiType)throws DAOException{
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        taxiTypeDAO.create(taxiType);
        return true;
    }

}
