package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiTypeDAO;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.TaxiType;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TaxiTypesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaxiTypesService.class);
    private final static TaxiTypesService TAXI_TYPES_SERVICE = new TaxiTypesService();
    private IDAOFactory daoFactory = DAOFactory.getInstance();
    private TaxiTypesService(){
        daoFactory = DAOFactory.getInstance();
    }
    public static TaxiTypesService getTaxiTypesService(){
        return TAXI_TYPES_SERVICE;
    }
    public List<TaxiType> getAllTaxiTypes() throws DAOException{
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        List<TaxiType> taxiTypes = taxiTypeDAO.findAll();
        return taxiTypes;
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
