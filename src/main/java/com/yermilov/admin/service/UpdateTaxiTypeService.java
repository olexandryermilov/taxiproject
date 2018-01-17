package com.yermilov.admin.service;

import com.yermilov.dao.*;
import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UpdateTaxiTypeService {
    private final static Logger logger = LoggerFactory.getLogger(UpdateTaxiTypeService.class);
    private final static UpdateTaxiTypeService TAXI_TYPE_SERVICE = new UpdateTaxiTypeService();
    private IDAOFactory daoFactory;
    private UpdateTaxiTypeService(){
        daoFactory= DAOFactory.getInstance();
    }
    public static UpdateTaxiTypeService getTaxiTypeService(){
        return TAXI_TYPE_SERVICE;
    }

    public boolean updateTaxiType(TaxiType entity) throws DAOException {
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        taxiTypeDAO.update(entity);
        return true;
    }
}
