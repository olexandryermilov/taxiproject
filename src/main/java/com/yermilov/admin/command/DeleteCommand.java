package com.yermilov.admin.command;

import com.yermilov.admin.service.DeleteService;
import com.yermilov.admin.service.LoginService;
import com.yermilov.admin.service.UsersService;
import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import com.yermilov.command.LoginCommand;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeleteService deleteService = DeleteService.getDeleteService();
        List<Integer> idToDelete = Arrays.stream(request.getParameterValues("toDelete"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        StringBuilder idsToDelete = new StringBuilder();
        for(Integer i: idToDelete){
            idsToDelete.append(i).append(" ");
        }
        LOGGER.info("Trying to delete next ids:{}",idsToDelete.toString());
        try {
            deleteService.delete(idToDelete);
            LOGGER.info("Successfully deleted");
            request.setAttribute("users", UsersService.getUsersService().getAllUsers());//todo: clear this up somehow
            request.getRequestDispatcher("users.jsp?command=users").forward(request,response);
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

