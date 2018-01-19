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
        UsersService usersService = UsersService.getUsersService();
        try{
            String pageNumberParam = req.getParameter("pageNumber");
            String pageSizeParam = req.getParameter("pageSize");
            if(pageNumberParam==null){
                pageNumberParam="1";
            }
            if(pageSizeParam==null)pageSizeParam="2";
            int pageNum = Integer.parseInt(pageNumberParam);
            int pageSize = Integer.parseInt(pageSizeParam);

            List<User> allUsers = usersService.getUsers(pageNum,pageSize);
            req.setAttribute("pageAmount",usersService.getTableSize()/pageSize);
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
