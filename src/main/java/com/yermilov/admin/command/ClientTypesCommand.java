package com.yermilov.admin.command;

import com.yermilov.admin.service.ClientTypesService;
import com.yermilov.admin.service.TaxiTypesService;
import com.yermilov.command.Command;
import com.yermilov.domain.ClientType;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientTypesCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientTypesCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LOGGER.info("Showing all clienttypes to admin");
            ClientTypesService clientTypesService = ClientTypesService.getClientTypesService();
            List<ClientType> allClientTypes = clientTypesService.getAllClientTypes();
            req.setAttribute("clienttypes",allClientTypes);
            req.getRequestDispatcher("clienttypes.jsp").forward(req,resp);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}
