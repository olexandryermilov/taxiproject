package com.yermilov.command;

import com.yermilov.domain.ClientType;
import com.yermilov.domain.TaxiType;
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

public class RideOrderCommandTest {
    private static List<TaxiType> taxiTypeList;
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @BeforeClass
    public static void initDatabase() throws SQLException {
        taxiTypeList = TableCreator.initTaxiTypeTable();
    }

    @Test
    public void execute_GivesAllTaxiTypes() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.RIDE+".jsp")).thenReturn(requestDispatcher);
        Command rideOrderCommand = CommandFactory.getInstance().getCommand(CommandFactory.RIDE_ORDER);
        rideOrderCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("taxitypes", taxiTypeList);
    }
    @AfterClass
    public static void cleanDatabase() throws SQLException {
        TableCleaner.cleanTaxiTypeTable();
    }
}
