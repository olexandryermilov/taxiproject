package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TimeCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(TimeCalculationService.class);
    private final static TimeCalculationService TIME_CALCULATION_SERVICE = new TimeCalculationService();

    private TimeCalculationService(){
    }
    public static TimeCalculationService getTimeCalculationService(){
        return TIME_CALCULATION_SERVICE;
    }

    public double getTime(String from, String to) {
        return new java.util.Random(new Date().getTime()).nextInt(7);
    }
    public double getTime(double distance) {
        return new java.util.Random(new Date().getTime()).nextInt(7);
    }
}
