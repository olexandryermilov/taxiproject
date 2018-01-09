package com.yermilov.servlet;

import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet{

    private final static Logger LOGGER = LoggerFactory.getLogger(ServletDispatcher.class);
    @Override
    public void init() throws ServletException {
        super.init();
        DOMConfigurator.configure("log4j.xml");
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(request.getParameter("command"));
        LOGGER.info("Got command {}",command);
        try {
            command.execute(request,response);
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.error(e.getMessage());

        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.error(e.getMessage());
        }
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
