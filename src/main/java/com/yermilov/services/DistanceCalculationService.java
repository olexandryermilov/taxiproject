package com.yermilov.services;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class DistanceCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(DistanceCalculationService.class);
    private final static DistanceCalculationService DISTANCE_CALCULATION_SERVICE = new DistanceCalculationService();
    private DistanceCalculationService(){

    }
    public static DistanceCalculationService getDistanceCalculationService(){
        return DISTANCE_CALCULATION_SERVICE;
    }

    public float getDistance(String from, String to) throws DAOException {
        return new Random(42).nextFloat()*new Random(43).nextInt(100);//todo:implement!
    }
}
