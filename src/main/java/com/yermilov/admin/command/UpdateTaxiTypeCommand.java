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
        double fare = Double.parseDouble(request.getParameter("fare"));
        String taxiTypeName = request.getParameter("name");
        UpdateTaxiTypeService updateTaxiTypeService = UpdateTaxiTypeService.getTaxiTypeService();
        try {
            LOGGER.info("Trying to update taxitype {}",taxiTypeId);
            TaxiType taxiType = new TaxiType(fare,taxiTypeName);
            taxiType.setTaxiTypeId(taxiTypeId);
            updateTaxiTypeService.updateTaxiType(taxiType);
            LOGGER.info("Successfully updated taxitype {}",taxiTypeId);
            request.setAttribute("errorMessage","TaxiType added");
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

