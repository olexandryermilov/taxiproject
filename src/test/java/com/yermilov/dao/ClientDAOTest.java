package com.yermilov.dao;

import com.yermilov.TableCreator;
import com.yermilov.domain.Client;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClientDAOTest{
    private static List<Client> allClients;
    private static List<User> allUsers;

    @Before
    public void initDatabase() throws SQLException {
        allUsers = TableCreator.initUserTable();
        allClients = TableCreator.initClientTable();
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @After
    public void dropTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_DROP_CLIENT = "DROP TABLE `client`";
        PreparedStatement ps = connection.prepareStatement(SQL_DROP_CLIENT);
        ps.execute();
        String SQL_DROP_USER = "DROP TABLE `user`";
        ps = connection.prepareStatement(SQL_DROP_USER);
        ps.execute();
    }

    @Test
    public void findClientByUserId_ReturnsCorrectClient_WhenCorrectUserId() throws DAOException {
        final int USER_ID=1;
        ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
        assertEquals(allClients.get(0),clientDAO.findClientByUserId(USER_ID));
    }
    @Test
    public void findClientByUserId_ReturnsNull_WhenWrongUserId() throws DAOException {
        final int USER_ID=15;
        ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
        assertNull(clientDAO.findClientByUserId(USER_ID));
    }
    @Test
    public void delete_DeletesFromDatabase_WhenCorrectData() throws DAOException {
        final Client CLIENT_TO_DELETE = allClients.get(0);
        ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
        clientDAO.delete(CLIENT_TO_DELETE);
        assertNull(clientDAO.findClientByUserId(CLIENT_TO_DELETE.getUserId()));
    }

    @Test
    public void create_CreatesNewRecord_WhenCorrectData() throws DAOException{
        final Client CLIENT_TO_ADD = new Client(4);
        CLIENT_TO_ADD.setClientId(4);
        ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
        clientDAO.create(CLIENT_TO_ADD);
        assertEquals(CLIENT_TO_ADD,clientDAO.findClientByUserId(4));
    }
}
