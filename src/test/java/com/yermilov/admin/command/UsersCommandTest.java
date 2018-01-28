package com.yermilov.admin.command;

import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import com.yermilov.domain.TaxiType;
import com.yermilov.domain.User;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersCommandTest {
    private static List<User> userList;
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @BeforeClass
    public static void initDatabase() throws SQLException {
        userList = TableCreator.initUserTable();
    }

    @Test
    public void execute_GivesAllUsers() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.USERS+".jsp")).thenReturn(requestDispatcher);
        when(httpServletRequest.getParameter(eq("pageNumber"))).thenReturn("1");
        when(httpServletRequest.getParameter(eq("pageSize"))).thenReturn("5");


        Command usersCommand = CommandFactory.getInstance().getCommand(CommandFactory.USERS);
        usersCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("pageAmount", 1);
        verify(httpServletRequest).setAttribute("users", userList.subList(0,userList.size()));
    }
    @Test
    public void execute_SkipsTwoUsers() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.USERS+".jsp")).thenReturn(requestDispatcher);
        when(httpServletRequest.getParameter(eq("pageNumber"))).thenReturn("2");
        when(httpServletRequest.getParameter(eq("pageSize"))).thenReturn("2");


        Command usersCommand = CommandFactory.getInstance().getCommand(CommandFactory.USERS);
        usersCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("pageAmount", 2);
        verify(httpServletRequest).setAttribute("users", userList.subList(2,Math.min(userList.size(),2+2)));
    }
    @AfterClass
    public static void cleanDatabase() throws SQLException {
        TableCleaner.cleanUserTable();
    }
}
