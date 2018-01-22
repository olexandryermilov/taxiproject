package com.yermilov.services;

import com.yermilov.dao.*;
import com.yermilov.domain.Client;
import com.yermilov.domain.Taxi;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service for calculating cost of ride
 * @see com.yermilov.command.CostCalculationCommand
 */
public class CostCalculationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CostCalculationService.class);
    private final static CostCalculationService COST_CALCULATION_SERVICE = new CostCalculationService();
    private final static double MIN_RIDE_COST = 10;
    private IDAOFactory daoFactory;
    private CostCalculationService(){
            daoFactory=DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static CostCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }

    /**
     *
     * @param userId Id of user to look for
     * @return Entity of Client
     * @throws DAOException Re-throws DAOException from ClientDAO
     * @see ClientDAO#findClientByUserId(int)
     */
    public Client getClient(int userId) throws DAOException {
        ClientDAO clientDAO = daoFactory.getClientDAO();
        Client client = clientDAO.findClientByUserId(userId); //todo: refactor
        return client;
    }

    /**
     * @param taxi Taxi which is supposed to drive client
     * @param distance Distance between start and finish
     * @param discount Client's discount
     * @return Cost of ride
     * @throws DAOException Re-throws DAOException from getFare method
     * @see #getFare(int)
     */
    public double getDriveCost(Taxi taxi, double distance, int discount) throws DAOException {
        double fare = getFare(taxi.getTaxiTypeId());
        double driveCost = MIN_RIDE_COST+(fare*distance/100)*(100-discount);
        return new BigDecimal(driveCost).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     *
     * @param taxitypeid Id of taxitype to get fare for
     * @return Cost of 1 kilometer for specific taxitype
     * @throws DAOException Re-throws DAOException from TaxiTypeDAO
     * @see TaxiTypeDAO#findById(int)
     */
    public double getFare(int taxitypeid) throws DAOException {
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        TaxiType taxiType = taxiTypeDAO.findById(taxitypeid);
        return taxiType.getFare();
    }
}
