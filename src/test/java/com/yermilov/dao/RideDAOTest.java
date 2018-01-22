package com.yermilov.dao;

import com.yermilov.TableCreator;
import com.yermilov.domain.*;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class RideDAOTest {
    private static List<Ride> allRidesList;
    private static List<Driver> allDriversList;
    private static List<User> allUsersList;
    private static List<Client> allClientsList;
    private static List<TaxiType> allTaxiTypesList;
    private static List<Taxi> allTaxiesList;
    private static List<Ride> firstClientRides;


    @Before
    public void initTable() throws SQLException {
        allUsersList = TableCreator.initUserTable();
        allClientsList = TableCreator.initClientTable();
        allDriversList = TableCreator.initDriverTable();
        allTaxiTypesList = TableCreator.initTaxiTypeTable();
        allTaxiesList = TableCreator.initTaxiTable();
        allRidesList = TableCreator.initRideTable();
        firstClientRides = allRidesList.subList(0,allRidesList.size()-1);
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @After
    public void dropTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();

        String SQL_DROP = "DROP TABLE `ride`";
        PreparedStatement ps = connection.prepareStatement(SQL_DROP);
        ps.execute();
        SQL_DROP = "DROP TABLE `taxi`";
        ps = connection.prepareStatement(SQL_DROP);
        ps.execute();
        SQL_DROP = "DROP TABLE `taxitype`";
        ps = connection.prepareStatement(SQL_DROP);
        ps.execute();
        String SQL_DROP_USER = "DROP TABLE `user`";
        ps = connection.prepareStatement(SQL_DROP_USER);
        ps.execute();
        SQL_DROP = "DROP TABLE `driver`";
        ps = connection.prepareStatement(SQL_DROP);
        ps.execute();
        SQL_DROP = "DROP TABLE `client`";
        ps = connection.prepareStatement(SQL_DROP);
        ps.execute();

    }

    @Test
    public void create_AddsRideToDatabase() throws DAOException {
        final Ride RIDE = new Ride(1,3,1,38.0,27.0,
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        RIDE.setRideId(5);
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        rideDAO.create(RIDE);
        List<Ride> rides = new ArrayList<>();
        rides.add(RIDE);
        Client client = new Client(3);
        client.setClientId(3);
        assertEquals(rides,rideDAO.findRidesForClient(client,0,1));
    }

    @Test
    public void getMoneySpentForClient() throws DAOException {
        final double MONEY_SPENT = firstClientRides.stream().mapToDouble(Ride::getCost).sum();
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(1);
        client.setClientId(1);
        assertEquals(MONEY_SPENT,rideDAO.getMoneySpentForClient(client),0.01);
    }

    @Test
    public void getMoneySpentForClient_ReturnsZero_WhenNoRides() throws DAOException {
        final double MONEY_SPENT = 0.0;
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(3);
        client.setClientId(3);
        assertEquals(MONEY_SPENT,rideDAO.getMoneySpentForClient(client),0.01);
    }

    @Test
    public void findRidesForClient_ReturnsAllRides()throws DAOException{
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(1);
        client.setClientId(1);
        assertEquals(firstClientRides,rideDAO.findRidesForClient(client,0,3));
    }
    @Test
    public void findRidesForClient_SkipsRides()throws DAOException{
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(1);
        client.setClientId(1);
        assertEquals(firstClientRides.subList(1,3),rideDAO.findRidesForClient(client,1,3));
    }
    @Test
    public void findRidesForClient_LimitsAmount()throws DAOException{
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(1);
        client.setClientId(1);
        assertEquals(firstClientRides.subList(0,2),rideDAO.findRidesForClient(client,0,2));
    }

    @Test
    public void findSize_GivesCorrectSize_WhenNonZero() throws DAOException {
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(1);
        client.setClientId(1);
        assertEquals(firstClientRides.size(),rideDAO.findSize(client));
    }
    @Test
    public void findRidesForClient_ReturnsNullWhenNoRides()throws DAOException{
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(4);
        client.setClientId(4);
        assertEquals(new ArrayList<>(),rideDAO.findRidesForClient(client,0,1));
    }

    @Test
    public void findSize_GivesZero_WhenNoRidesForClient() throws DAOException {
        RideDAO rideDAO = DAOFactory.getInstance().getRideDAO();
        Client client = new Client(4);
        client.setClientId(4);
        assertEquals(0,rideDAO.findSize(client));
    }
}
