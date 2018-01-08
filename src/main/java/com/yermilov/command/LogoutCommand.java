package com.yermilov.command;

import com.yermilov.exceptions.DAOException;
import com.yermilov.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("email");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
