package com.yermilov.command;

import com.yermilov.domain.Client;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.domain.User;
import com.yermilov.exceptions.CostCalculationException;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CostCalculationCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(CostCalculationCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        if(from==null||to==null){
            request.setAttribute("errorMessage","You should fill all the fields");
            request.getRequestDispatcher(CommandFactory.CALCULATE_COST+".jsp").forward(request, response);
            return;
        }
        DistanceCalculationService distanceCalculationService = DistanceCalculationService.getDistanceCalculationService();
        int userId=((User)(request.getSession().getAttribute("currentUser"))).getUserId();
        CostCalculationService costCalculationService = CostCalculationService.getCostCalculationService();
        try {
            double distance = distanceCalculationService.getDistance(from,to);
            Client client =costCalculationService.getClient(userId);
            int discount = ClientTypeCalculationService.getCostCalculationService().getClientsDiscount(client);
            Taxi taxi = GetCarService.getGetCarService().getCar();
            double cost = costCalculationService.getDriveCost(taxi,distance,discount);
            request.setAttribute("cost",cost);
            request.getSession().setAttribute("client",client);
            request.setAttribute("distance",distance);
            request.setAttribute("discount",discount);
            request.setAttribute("taxi",taxi);
            Driver driver = TaxiIdentifierService.getTaxiIdentifierService().getDriver(taxi);
            request.setAttribute("driver",driver);
            request.setAttribute("arrivalTime",TimeCalculationService.getTimeCalculationService().getTime(from,to));
            request.getRequestDispatcher("ride.jsp").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        } catch (CostCalculationException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

