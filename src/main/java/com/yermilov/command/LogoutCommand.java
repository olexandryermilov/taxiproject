package com.yermilov.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("currentUser");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}
