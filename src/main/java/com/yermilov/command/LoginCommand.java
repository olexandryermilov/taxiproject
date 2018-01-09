package com.yermilov.command;

import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")!=null){
            request.setAttribute("errorMessageLogin", "User already logged in");
            request.getRequestDispatcher(CommandFactory.LOGIN+".jsp").forward(request, response);
            return;
        }
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
            User user = loginService.getUser(email,password);
            if (user!=null) {
                LOGGER.info("User {} logged in.",email);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } else {
                LOGGER.info("User {} couldn't log in.",email);
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

