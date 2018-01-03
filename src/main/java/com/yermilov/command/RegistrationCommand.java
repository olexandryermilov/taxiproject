package com.yermilov.command;

import com.yermilov.exceptions.RegistrationException;
import com.yermilov.services.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        if (login == null || password == null || name == null || surname == null) {
            req.setAttribute("errorMessage", "All fields must be filled");
            req.getRequestDispatcher(CommandFactory.REGISTRATION).forward(req, resp);
            return;
        }
        RegistrationService registrationService = RegistrationService.getRegistrationService();
        try {
            registrationService.register(login, password, name, surname);
        } catch (RegistrationException e) {
            //todo:log
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(CommandFactory.REGISTRATION).forward(req, resp);
        }

    }
}
