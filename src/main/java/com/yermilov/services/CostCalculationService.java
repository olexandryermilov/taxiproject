package com.yermilov.services;

import com.yermilov.dao.ClientDAO;
import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.RideDAO;
import com.yermilov.domain.Client;
import com.yermilov.domain.ClientType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CostCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(CostCalculationService.class);
    private final static CostCalculationService COST_CALCULATION_SERVICE = new CostCalculationService();
    private final static double MIN_RIDE_COST = 10;//todo:remove somewhere
    private final static double RATE = 1.0;
    private CostCalculationService(){

    }
    public static CostCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }


    public Client getClient(int userId) throws DAOException {
        ClientDAO clientDAO = DAOFactory.getClientDAO();
        Client client = clientDAO.findClientByUserId(userId);
        return client;
    }
    public double getDriveCost(double distance, int discount) throws DAOException{
        double driveCost = MIN_RIDE_COST+(RATE*distance/100)*(100-discount);
        return new BigDecimal(driveCost).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
