package com.yermilov.admin.command;

import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.DriverDAO;
import com.yermilov.domain.Driver;
import com.yermilov.domain.TaxiType;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegisterDriverCommandTest {
    private static List<Driver> driverList;
    private static List<User> userList;
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @BeforeClass
    public static void initDatabase() throws SQLException {
        userList = TableCreator.initUserTable();
        driverList = TableCreator.initDriverTable();
    }

    @Test
    public void execute_RegistersDriver() throws ServletException, IOException, DAOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher("controller?command=users&pageNumber=1")).thenReturn(requestDispatcher);
        when(httpServletRequest.getParameter("userid")).thenReturn("4");
        Command registerDriverCommand = CommandFactory.getInstance().getCommand(CommandFactory.REGISTER_DRIVER);
        Driver driver = new Driver(4);
        driver.setDriverId(4);
        registerDriverCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("errorMessage","Successfully registered user as driver");
        assertEquals(driver, DAOFactory.getInstance().getDriverDAO().findByUserId(4));
    }
    @AfterClass
    public static void cleanDatabase() throws SQLException {
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }
}
