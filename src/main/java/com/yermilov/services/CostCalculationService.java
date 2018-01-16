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

public class CostCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(CostCalculationService.class);
    private final static CostCalculationService COST_CALCULATION_SERVICE = new CostCalculationService();
    private final static double MIN_RIDE_COST = 10;
    private IDAOFactory daoFactory;
    private CostCalculationService(){
            daoFactory=DAOFactory.getInstance();
    }
    public static CostCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }


    public Client getClient(int userId) throws DAOException {
        ClientDAO clientDAO = daoFactory.getClientDAO();
        Client client = clientDAO.findClientByUserId(userId);
        return client;
    }
    public double getDriveCost(Taxi taxi, double distance, int discount) throws DAOException {
        double fare = getFare(taxi.getTaxiTypeId());
        double driveCost = MIN_RIDE_COST+(fare*distance/100)*(100-discount);
        return new BigDecimal(driveCost).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public double getFare(int taxitypeid) throws DAOException {
        TaxiTypeDAO taxiTypeDAO = daoFactory.getTaxiTypeDAO();
        TaxiType taxiType = taxiTypeDAO.findById(taxitypeid);
        return taxiType.getFare();
    }
}
