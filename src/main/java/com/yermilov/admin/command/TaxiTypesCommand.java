package com.yermilov.admin.command;

import com.yermilov.admin.service.TaxiTypesService;
import com.yermilov.command.Command;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TaxiTypesCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaxiTypesCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            TaxiTypesService taxiTypesService = TaxiTypesService.getTaxiTypesService();
            List<TaxiType> allTaxiTypes = taxiTypesService.getAllTaxiTypes();
            req.setAttribute("taxitypes",allTaxiTypes);
            req.getRequestDispatcher("taxitypes.jsp").forward(req,resp);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}
