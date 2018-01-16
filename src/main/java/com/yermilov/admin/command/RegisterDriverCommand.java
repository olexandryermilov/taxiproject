package com.yermilov.admin.command;

import com.yermilov.admin.service.RegisterDriverService;
import com.yermilov.admin.service.UsersService;
import com.yermilov.command.Command;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.DriverRegistrationException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterDriverCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterDriverCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterDriverService registerDriverService = RegisterDriverService.getRegisterDriverService();
        int userId= Integer.parseInt(request.getParameter("userid"));
        LOGGER.info("Trying to register as driver user with userid={}",userId);
        try {
            registerDriverService.registerDriver(userId);
            LOGGER.info("Successfully registered user {}",userId);
            request.setAttribute("errorMessage","Successfully registered user as driver");
            request.getRequestDispatcher("controller?command=users").forward(request,response);
        }catch (DriverRegistrationException e){
            LOGGER.error(e.getMessage());
            request.setAttribute("errorMessage",e.getMessage());
            request.getRequestDispatcher("controller?command=users").forward(request,response);
        }
        catch (DAOException e) {
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

