package com.yermilov.command;

import com.yermilov.domain.*;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.AfterClass;
import org.junit.Before;
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
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RideStatisticsCommandTest {
    private static List<Ride> allRidesList;
    private static List<Driver> allDriversList;
    private static List<User> allUsersList;
    private static List<Client> allClientsList;
    private static List<TaxiType> allTaxiTypesList;
    private static List<Taxi> allTaxiesList;

    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }

    @BeforeClass
    public static void initDatabase() throws SQLException {
        allUsersList = TableCreator.initUserTable();
        allClientsList = TableCreator.initClientTable();
        allDriversList = TableCreator.initDriverTable();
        allTaxiTypesList = TableCreator.initTaxiTypeTable();
        allTaxiesList = TableCreator.initTaxiTable();
        allRidesList = TableCreator.initRideTable();
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }

    @Test
    public void execute_givesAllRides() throws ServletException, IOException {
        for(Client client : allClientsList){
            List<Ride> rides = allRidesList.stream()
                    .filter(ride -> ride.getClientId()==client.getClientId())
                    .collect(Collectors.toList());
            HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
            HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
            HttpSession httpSession = mock(HttpSession.class);

            when(httpSession.getAttribute("currentUser")).thenReturn(allUsersList.get(client.getUserId()-1));
            when(httpServletRequest.getSession()).thenReturn(httpSession);

            RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
            when(httpServletRequest.getRequestDispatcher(CommandFactory.RIDES_STATISTICS+".jsp")).thenReturn(requestDispatcher);
            when(httpServletRequest.getParameter(eq("pageNumber"))).thenReturn("1");
            when(httpServletRequest.getParameter(eq("pageSize"))).thenReturn(Integer.toString(Math.max(1,rides.size())));

            Command command = CommandFactory.getInstance().getCommand(CommandFactory.RIDES_STATISTICS);
            command.execute(httpServletRequest,httpServletResponse);
            verify(httpServletRequest).setAttribute(eq("carNumbers"),any(List.class));
            verify(httpServletRequest).setAttribute(eq("rides"),eq(rides));
            verify(httpServletRequest).setAttribute(eq("pageAmount"), eq(1));


        }
    }

    @AfterClass
    public static void cleanDatabase() throws SQLException{
        TableCleaner.cleanRideTable();
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();
    }
}
