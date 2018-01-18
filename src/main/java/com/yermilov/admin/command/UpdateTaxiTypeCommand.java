package com.yermilov.admin.command;

import com.yermilov.admin.service.LoginService;
import com.yermilov.admin.service.UpdateTaxiTypeService;
import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import com.yermilov.domain.Admin;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateTaxiTypeCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(com.yermilov.command.LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taxiTypeId = Integer.parseInt(request.getParameter("taxitypeid"));
        String taxiTypeName = request.getParameter("name");
        double fare;
        try{
            fare=Double.parseDouble(request.getParameter("fare"));;
        }
        catch (NumberFormatException e){
            request.setAttribute("errorMessage","Fare should be a number");
            request.getRequestDispatcher(CommandFactory.UPDATE_TAXITYPE+".jsp").forward(request, response);
            return;
        }
        if(taxiTypeName ==null){
            request.setAttribute("errorMessage","You should fill all the fields");
            request.getRequestDispatcher(CommandFactory.UPDATE_TAXITYPE+".jsp").forward(request, response);
            return;
        }
        UpdateTaxiTypeService updateTaxiTypeService = UpdateTaxiTypeService.getTaxiTypeService();
        try {
            LOGGER.info("Trying to update taxitype {}",taxiTypeId);
            TaxiType taxiType = new TaxiType(fare,taxiTypeName);
            taxiType.setTaxiTypeId(taxiTypeId);
            updateTaxiTypeService.updateTaxiType(taxiType);
            LOGGER.info("Successfully updated taxitype {}",taxiTypeId);
            request.setAttribute("errorMessage","TaxiType edited");
            request.getRequestDispatcher("controller?command=taxitypes").forward(request,response);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}

