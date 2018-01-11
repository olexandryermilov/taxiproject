package com.yermilov.admin.command;

import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import com.yermilov.admin.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(com.yermilov.command.LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) {
            request.getRequestDispatcher("error.jsp");
            LOGGER.info("Empty email");
        }
        if (password == null) {
            request.getRequestDispatcher("error.jsp");
            LOGGER.info("Empty password");
        }
        LoginService loginService = LoginService.getLoginService();
        try {
            Admin admin = loginService.getAdmin(email,password);
            if (admin!=null) {
                LOGGER.info("User {} logged into admin.",email);
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                LOGGER.info("User {} couldn't log into admin.",email);
                request.setAttribute("errorMessageLogin", "Login or password incorrect");
                request.getRequestDispatcher(CommandFactory.LOGIN+".jsp").forward(request, response);
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

