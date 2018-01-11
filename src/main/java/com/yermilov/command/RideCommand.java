package com.yermilov.command;

import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.ClientTypeCalculationService;
import com.yermilov.services.CostCalculationService;
import com.yermilov.services.DistanceCalculationService;
import com.yermilov.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RideCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        DistanceCalculationService distanceCalculationService = DistanceCalculationService.getDistanceCalculationService();
        int userId=((User)(request.getSession().getAttribute("currentUser"))).getUserId();
        CostCalculationService costCalculationService = CostCalculationService.getCostCalculationService();

        try {
            double distance = distanceCalculationService.getDistance(from,to);
            int discount = ClientTypeCalculationService.getCostCalculationService().getClientsDiscount(userId);
            double cost = costCalculationService.getDriveCost(distance,discount);
            request.setAttribute("cost",cost);
            request.getRequestDispatcher("rideInformation.jsp").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

