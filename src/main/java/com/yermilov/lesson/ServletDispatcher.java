package com.yermilov.lesson;

import com.yermilov.command.Command;
import com.yermilov.command.FactoryCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet{

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        FactoryCommand factory = FactoryCommand.getInstance();
        Command command = factory.getCommand((String) request.getAttribute("command"));
        command.execute(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
}
