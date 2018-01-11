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

public class CostCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(CostCalculationService.class);
    private final static CostCalculationService COST_CALCULATION_SERVICE = new CostCalculationService();
    private final static double MIN_RIDE_COST = 10;//todo:remove somewhere
    private CostCalculationService(){

    }
    public static CostCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }


    public double getDriveCost(double distance, int discount) throws DAOException{
        double basicPrice = 1.0;
        double driveCost = MIN_RIDE_COST+(basicPrice*distance/100)*(100-discount);
        return driveCost;
    }
}
