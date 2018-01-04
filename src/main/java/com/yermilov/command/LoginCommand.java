package com.yermilov.command;

import com.yermilov.exceptions.DAOException;
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
        try {
            if (loginService.verify(login, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("login", login);
                request.getRequestDispatcher(CommandFactory.USERS).forward(request, response);
            } else {
                request.setAttribute("errorMessageLogin", "Login incorrect");
                request.getRequestDispatcher(CommandFactory.LOGIN).forward(request, response);
            }
        } catch (DAOException e) {
            //todo:log
        }
    }
}

