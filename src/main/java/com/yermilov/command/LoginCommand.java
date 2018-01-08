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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null) {
            request.getRequestDispatcher("error.jsp");//todo: add two files
        }
        if (password == null) {
            request.getRequestDispatcher("error.jsp");
        }
        LoginService loginService = LoginService.getLoginService();
        try {
            if (loginService.verify(email, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessageLogin", "Login incorrect");
                request.getRequestDispatcher(CommandFactory.LOGIN+".jsp").forward(request, response);
            }
        } catch (DAOException e) {
            //todo:log
        }
    }
}

