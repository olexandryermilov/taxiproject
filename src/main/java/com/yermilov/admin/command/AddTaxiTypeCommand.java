package com.yermilov.admin.command;

import com.yermilov.admin.service.AddTaxiTypeService;
import com.yermilov.command.Command;
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

public class AddTaxiTypeCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddTaxiTypeCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddTaxiTypeService addTaxiTypeService =AddTaxiTypeService.getAddTaxiTypeService();
        try {
            LOGGER.info("Trying to add taxitype");
            String taxiTypeName = request.getParameter("name");
            Double fare = Double.valueOf(request.getParameter("fare"));
            TaxiType taxi = new TaxiType(fare,taxiTypeName);
            addTaxiTypeService.addTaxiType(taxi);
            LOGGER.info("Successfully added driver");
            request.setAttribute("errorMessage","TaxiType added");
            request.getRequestDispatcher("controller?command=taxitypes").forward(request,response);
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

