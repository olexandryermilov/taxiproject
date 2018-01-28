package com.yermilov.admin.command;

import com.yermilov.admin.service.AddClientTypeService;
import com.yermilov.admin.service.AddTaxiTypeService;
import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
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
        String name = request.getParameter("name");
        double moneyspent;
        int discount;
        try{
            moneyspent = Double.parseDouble(request.getParameter("moneyspent"));
            discount = Integer.parseInt(request.getParameter("discount"));
        }
        catch (NumberFormatException e){
            request.setAttribute("errorMessage","Moneyspent and discount should be a number");
            request.getRequestDispatcher(CommandFactory.ADD_CLIENTTYPE+".jsp").forward(request, response);
            return;
        }
        if(discount>100){
            request.setAttribute("errorMessage","Discount should be smaller than 100");
            request.getRequestDispatcher(CommandFactory.ADD_CLIENTTYPE+".jsp").forward(request, response);
            return;
        }
        if(discount<0||moneyspent<0){
            request.setAttribute("errorMessage","Discount and money spent should be positive");
            request.getRequestDispatcher(CommandFactory.ADD_CLIENTTYPE+".jsp").forward(request, response);
            return;
        }
        if(name==null){
            request.setAttribute("errorMessage","You should fill all the fields");
            request.getRequestDispatcher(CommandFactory.ADD_CLIENTTYPE+".jsp").forward(request, response);
            return;
        }
        try {
            LOGGER.info("Trying to add clienttype");
            ClientType clientType = new ClientType(discount,name,moneyspent);
            addClientTypeService.addClientType(clientType);
            LOGGER.info("Successfully added clienttype");
            request.setAttribute("errorMessage","ClientType added");
            request.getRequestDispatcher("controller?command=clienttypes").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

