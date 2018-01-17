package com.yermilov.admin.command;

import com.yermilov.admin.service.AddClientTypeService;
import com.yermilov.admin.service.AddTaxiTypeService;
import com.yermilov.command.Command;
import com.yermilov.domain.ClientType;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddClientTypeCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddClientTypeCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddClientTypeService addClientTypeService = AddClientTypeService.getAddClientTypeService();
        try {
            LOGGER.info("Trying to add clienttype");
            String name = request.getParameter("name");
            double moneyspent = Double.parseDouble(request.getParameter("moneyspent"));
            int discount = Integer.parseInt(request.getParameter("discount"));
            ClientType clientType = new ClientType(discount,name,moneyspent);
            addClientTypeService.addClientType(clientType);
            LOGGER.info("Successfully added clienttype");
            request.setAttribute("errorMessage","ClientType added");
            request.getRequestDispatcher("controller?command=clienttypes").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        } catch (TransactionException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

