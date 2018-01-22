package com.yermilov.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Service for calculation time
 * @see com.yermilov.command.CostCalculationCommand
 * @see com.yermilov.command.RideCommand
 */
public class TimeCalculationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TimeCalculationService.class);
    private final static TimeCalculationService TIME_CALCULATION_SERVICE = new TimeCalculationService();

    private TimeCalculationService(){
    }

    /**
     *
     * @return Instance of this class
     */
    public static TimeCalculationService getTimeCalculationService(){
        return TIME_CALCULATION_SERVICE;
    }

    /**
     * Not implemented, returns random time
     * @param from Starting point
     * @param to Finishing point
     * @return Time to go from start to finish
     */
    public double getTime(String from, String to) {
        return new java.util.Random(new Date().getTime()).nextInt(7)+1;
    }

    /**
     *
     * @param distance Distance to cover
     * @return Time to cover this distance
     */
    public double getTime(double distance) {
        return new java.util.Random(new Date().getTime()).nextInt(7)+1;
    }
}
