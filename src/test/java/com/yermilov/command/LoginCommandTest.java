package com.yermilov.command;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LoginCommandTest {
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
    public void execute_FailsLogin_WhenBadData() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute("currentUser")).thenReturn(null);
        when(httpServletRequest.getParameter("email")).thenReturn("bademail@gmail.com");
        when(httpServletRequest.getParameter("password")).thenReturn("password");
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.LOGIN+".jsp")).thenReturn(requestDispatcher);
        Command loginCommand = CommandFactory.getInstance().getCommand("login");
        loginCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("errorMessageLogin","Login or password incorrect");
    }
    @Test
    public void execute_FailsLogin_WhenEmailIsNull() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute("currentUser")).thenReturn(null);
        when(httpServletRequest.getParameter("email")).thenReturn(null);
        when(httpServletRequest.getParameter("password")).thenReturn("password");
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.LOGIN+".jsp")).thenReturn(requestDispatcher);
        Command loginCommand = CommandFactory.getInstance().getCommand(CommandFactory.LOGIN);
        loginCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("errorMessageLogin","You should fill email");
    }
    @Test
    public void execute_FailsLogin_WhenPasswordIsNull() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute("currentUser")).thenReturn(null);
        when(httpServletRequest.getParameter("email")).thenReturn("email@gmail.com");
        when(httpServletRequest.getParameter("password")).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.LOGIN+".jsp")).thenReturn(requestDispatcher);
        Command loginCommand = CommandFactory.getInstance().getCommand(CommandFactory.LOGIN);
        loginCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("errorMessageLogin","You should fill password");
    }

    @Test
    public void execute_LogsIn_WhenGoodData() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute("currentUser")).thenReturn(null);
        when(httpServletRequest.getParameter("email")).thenReturn(userList.get(0).getEmail());
        when(httpServletRequest.getParameter("password")).thenReturn(userList.get(0).getPassword());
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);
        Command loginCommand = CommandFactory.getInstance().getCommand(CommandFactory.LOGIN);
        loginCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpSession).setAttribute("currentUser", userList.get(0));
    }

    @Test
    public void execute_FailsLogIn_WhenAlreadyLoggedIn() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute("currentUser")).thenReturn(userList.get(0));
        when(httpServletRequest.getParameter("email")).thenReturn(userList.get(0).getEmail());
        when(httpServletRequest.getParameter("password")).thenReturn(userList.get(0).getPassword());
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);
        Command loginCommand = CommandFactory.getInstance().getCommand(CommandFactory.LOGIN);
        loginCommand.execute(httpServletRequest,httpServletResponse);
        verify(requestDispatcher).forward(httpServletRequest,httpServletResponse);
    }
    @AfterClass
    public static void cleanDatabase() throws SQLException {
        TableCleaner.cleanUserTable();
    }
}
