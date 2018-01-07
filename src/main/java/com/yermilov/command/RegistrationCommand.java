package com.yermilov.command;

import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.RegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.services.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        if (email == null || password == null || name == null || surname == null) {
            req.setAttribute("errorMessage", "All fields must be filled");
            req.getRequestDispatcher(CommandFactory.REGISTRATION).forward(req, resp);
            return;
        }
        RegistrationService registrationService = RegistrationService.getRegistrationService();
        try {
            registrationService.register(email, password, name, surname);
        } catch (RegistrationException e) {
            //todo:log
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(CommandFactory.REGISTRATION).forward(req, resp);
        }
        catch (SQLException e) {
            //todo:log
        } catch (TransactionException e) {
            //todo:log
        } catch (DAOException e) {
            //todo:log
        }

    }
}
