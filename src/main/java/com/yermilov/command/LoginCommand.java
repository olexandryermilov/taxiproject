package com.yermilov.command;

import com.yermilov.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null) {
            request.getRequestDispatcher("error.jsp");//todo: add two files
        }
        if (password == null) {
            request.getRequestDispatcher("error.jsp");
        }
        LoginService loginService = LoginService.getLoginService();
        if (loginService.verify(login, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            try {
                request.getRequestDispatcher(FactoryCommand.USERS).forward(request, response);
            } catch (ServletException e) {
                //logger.error(e);
            } catch (IOException e) {
                //logger.error(e);
            }
        } else {
            request.setAttribute("errorMessageLogin", "Login incorrect");
            request.getRequestDispatcher(FactoryCommand.LOGIN).forward(request, response);
        }
    }
}

