package com.yermilov.admin.command;

import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;
import com.yermilov.domain.ClientType;
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

import static org.mockito.Mockito.*;

public class ClientTypesCommandTest {
    private static List<ClientType> clientTypeList;
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @BeforeClass
    public static void initDatabase() throws SQLException {
        clientTypeList = TableCreator.initClientTypeTable();
    }

    @Test
    public void execute_GivesAllClientTypes() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(httpServletRequest.getRequestDispatcher(CommandFactory.CLIENTTYPES+".jsp")).thenReturn(requestDispatcher);
        Command clientTypesCommand = CommandFactory.getInstance().getCommand(CommandFactory.CLIENTTYPES);
        clientTypesCommand.execute(httpServletRequest,httpServletResponse);
        verify(httpServletRequest).setAttribute("clienttypes",clientTypeList.subList(1,clientTypeList.size()));
    }
    @AfterClass
    public static void cleanDatabase() throws SQLException {
        TableCleaner.cleanClientTypeTable();
    }
}
