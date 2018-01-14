package com.yermilov.services;

import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Random;

public class DistanceCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(DistanceCalculationService.class);
    private final static DistanceCalculationService DISTANCE_CALCULATION_SERVICE = new DistanceCalculationService();
    private DistanceCalculationService(){
    }
    public static DistanceCalculationService getDistanceCalculationService(){
        return DISTANCE_CALCULATION_SERVICE;
    }

    public double getDistance(String from, String to) {
        return new BigDecimal(new Random(new Date().getTime()).nextFloat()*new Random(43).nextInt(100)).setScale(2, RoundingMode.HALF_UP).doubleValue();//todo:implement!}
    }
}
