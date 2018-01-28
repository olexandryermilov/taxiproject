package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiDAO;
import com.yermilov.domain.Taxi;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

/**
 * Service for getting free car for client
 * @see com.yermilov.command.RideCommand
 */
public class GetCarService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GetCarService.class);
    private final static GetCarService getCarService = new GetCarService();
    private IDAOFactory daoFactory;
    private GetCarService(){
        daoFactory=DAOFactory.getInstance();
    }
    /**
     *
     * @return Instance of this class
     */
    public static GetCarService getGetCarService(){
        return getCarService;
    }

    /**
     * Not implemented now, returns one of all of cars in database
     * @param taxiType Type of taxi to get
     * @return Taxi record
     * @throws DAOException Re-throws DAOException from TaxiDAO
     * @see TaxiDAO#findById(int)
     */
    public Taxi getCar(TaxiType taxiType)throws DAOException{
        TaxiDAO taxiDAO =daoFactory.getTaxiDAO();
        int carNumber = taxiDAO.findNumberOfSpecifiedTaxiType(taxiType);
        int toSkip = new Random(new Date().getTime()).nextInt(carNumber);
        Taxi taxi = taxiDAO.findNthCar(toSkip,taxiType);
        return taxi;
    }
    public void setDaoFactory(IDAOFactory daoFactory){
        this.daoFactory=daoFactory;
    }
}
