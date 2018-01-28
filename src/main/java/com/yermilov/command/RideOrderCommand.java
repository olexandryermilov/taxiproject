package com.yermilov.command;

import com.yermilov.exceptions.DAOException;
import com.yermilov.services.RideOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RideOrderCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(RideOrderCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RideOrderService rideOrderService = RideOrderService.getRideOrderService();
        try {
            request.setAttribute("taxitypes",rideOrderService.getAllTaxiTypes());
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

