package com.yermilov.services;

import com.yermilov.dao.*;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service for getting all taxitypes to show them to user
 * @see com.yermilov.command.RideOrderCommand
 */
public class RideOrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideOrderService.class);
    private final static RideOrderService RIDE_ORDER_SERVICE = new RideOrderService();
    private IDAOFactory daoFactory;
    private RideOrderService(){
        daoFactory=DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static RideOrderService getRideOrderService(){
        return RIDE_ORDER_SERVICE;
    }

    /**
     *
     * @return List of all TaxiTypes
     * @throws DAOException Re-throws DAOExceptions from TaxiTypeDAO
     * @see TaxiTypeDAO#findAll()
     */
    public List<TaxiType> getAllTaxiTypes() throws DAOException {
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        return taxiTypeDAO.findAll();
    }


}
