package com.yermilov.admin.command;

import com.yermilov.command.Command;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.admin.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(UsersCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UsersService usersService = UsersService.getUsersService();
            List<User> allUsers = usersService.getAllUsers();
            req.setAttribute("users",allUsers);
            req.getRequestDispatcher("users.jsp").forward(req,resp);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Override
    public String toString(){
        return this.getClass().getName();
    }
}
