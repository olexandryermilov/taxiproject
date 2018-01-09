package com.yermilov.command;

import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.RegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command{
    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        if (email == null || password == null || name == null || surname == null) {
            LOGGER.info("Tried to register with empty fields.");
            req.setAttribute("errorMessage", "All fields must be filled");
            req.getRequestDispatcher(CommandFactory.REGISTRATION).forward(req, resp);
            return;
        }
        RegistrationService registrationService = RegistrationService.getRegistrationService();
        try {
            registrationService.register(email, password, name, surname);
            LOGGER.info("Registered new user: email :{} {} {}",email,name,surname);
            req.getRequestDispatcher("registration_success.jsp").forward(req,resp);
        } catch (RegistrationException e) {
            LOGGER.error(e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(CommandFactory.REGISTRATION).forward(req, resp);
        }
        catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } catch (TransactionException e) {
            LOGGER.error(e.getMessage());
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }

    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}
