package com.yermilov.command;

import com.yermilov.dao.ClientDAO;
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
            String pageNumberParam = request.getParameter("pageNumber");
            String pageSizeParam = request.getParameter("pageSize");
            if(pageNumberParam==null){
                pageNumberParam="1";
            }
            if(pageSizeParam==null)pageSizeParam="5";
            int pageNum = Integer.parseInt(pageNumberParam);
            int pageSize = Integer.parseInt(pageSizeParam);

            Client client = CostCalculationService.getCostCalculationService().
                    getClient(((User)request.getSession().getAttribute("currentUser")).getUserId());
            List<Ride> rideList;
            rideList=rideStatisticsService.getClientsRides(client,(pageNum-1)*pageSize,pageSize);
            List<String>carNumberList = new ArrayList<>();
            for(Ride ride : rideList){
                String carNumber = TaxiIdentifierService.getTaxiIdentifierService().getTaxi(ride.getTaxiId()).getCarNumber();
                carNumberList.add(carNumber);
            }
            request.setAttribute("rides",rideList);
            request.setAttribute("carNumbers",carNumberList);
            request.setAttribute("pageAmount",Math.max((rideStatisticsService.getTableSize(client)+pageSize-1)/pageSize,1));
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

