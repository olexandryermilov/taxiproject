package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiTypeDAO;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class AddTaxiTypeService {
    private final static Logger logger = LoggerFactory.getLogger(AddTaxiTypeService.class);
    private final static AddTaxiTypeService ADD_TAXI_TYPE_SERVICE = new AddTaxiTypeService();
    private IDAOFactory daoFactory;
    private AddTaxiTypeService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static AddTaxiTypeService getAddTaxiTypeService(){
        return ADD_TAXI_TYPE_SERVICE;
    }

    public boolean addTaxiType(TaxiType taxiType)throws DAOException, SQLException, TransactionException{
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        taxiTypeDAO.create(taxiType);
        return true;
    }

}
