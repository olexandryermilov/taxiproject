package com.yermilov.admin.command;

import com.yermilov.admin.service.AddCarService;
import com.yermilov.admin.service.UsersService;
import com.yermilov.command.Command;
import com.yermilov.domain.Driver;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddCarCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddCarCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddCarService addCarService =AddCarService.getAddCarService();
        int userId = Integer.parseInt(request.getParameter("userid"));
        try {
            Driver driver = addCarService.findDriverByUserId(userId);
            if(driver==null){
                request.setAttribute("errorMessage","First make this user a driver");
                request.getRequestDispatcher("controller?command=users").forward(request,response);
                return;
            }
            LOGGER.info("Trying to add car to driver with driverid={}",driver.getDriverId());
            String carNumber = request.getParameter("carnumber");
            int carType = Integer.parseInt(request.getParameter("cartype"));
            Taxi taxi = new Taxi(driver.getDriverId(),carType,carNumber);
            addCarService.addCar(taxi);
            LOGGER.info("Successfully added taxi");
            request.setAttribute("errorMessage","Car added");
            request.getRequestDispatcher("controller?command=users").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        } catch (TransactionException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

