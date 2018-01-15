package com.yermilov.command;

import com.yermilov.domain.Client;
import com.yermilov.domain.Ride;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.CostCalculationService;
import com.yermilov.services.RideStatisticsService;
import com.yermilov.services.TaxiIdentifierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RideStatisticsCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideStatisticsCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RideStatisticsService rideStatisticsService =RideStatisticsService.getRideStatisticsService();
        try {

            Client client = CostCalculationService.getCostCalculationService().getClient(((User)request.getSession().getAttribute("currentUser")).getUserId());
            List<Ride> rideList;
            rideList=rideStatisticsService.getClientsRides(client);
            List<String>carNumberList = new ArrayList<>();
            for(Ride ride : rideList){
                String carNumber = TaxiIdentifierService.getTaxiIdentifierService().getTaxi(ride.getTaxiId()).getCarNumber();
                carNumberList.add(carNumber);
            }
            request.setAttribute("rides",rideList);
            request.setAttribute("carNumbers",carNumberList);
            request.getRequestDispatcher("ridesStatistics.jsp").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

