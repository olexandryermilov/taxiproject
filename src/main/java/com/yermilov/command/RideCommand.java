package com.yermilov.command;

import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.RideStatisticsService;
import com.yermilov.services.TaxiIdentifierService;
import com.yermilov.services.TimeCalculationService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RideCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RideStatisticsService rideStatisticsService =RideStatisticsService.getRideStatisticsService();
        Client client = (Client) request.getSession().getAttribute("client");
        System.out.println(client);
        double cost = Double.parseDouble((request.getParameter("cost")));
        double distance = Double.parseDouble(request.getParameter("distance"));
        String carNumber = request.getParameter("carNumber");
        try {
            TaxiIdentifierService taxiIdentifierService=TaxiIdentifierService.getTaxiIdentifierService();
            Taxi taxi = taxiIdentifierService.getTaxi(carNumber);
            Driver driver = taxiIdentifierService.getDriver(taxi);
            Long timeNow = new DateTime().getMillis();
            Long rideTime = (long)(TimeCalculationService.getTimeCalculationService().getTime(distance)*60000);
            rideStatisticsService.putRideToDatabase(client, driver,taxi,cost,distance,
                    new Date(timeNow), new Date(timeNow+rideTime));
            request.setAttribute("approved",true);
            request.setAttribute("cost",cost);
            request.setAttribute("distance",distance);
            request.setAttribute("discount",request.getParameter("discount"));
            request.setAttribute("carNumber","AA1111AA");
            request.setAttribute("driver",new Driver(1));
            request.getRequestDispatcher("ride.jsp").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

