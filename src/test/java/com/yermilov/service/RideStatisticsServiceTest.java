package com.yermilov.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.domain.*;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.RideStatisticsService;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class RideStatisticsServiceTest {

    private List<Client> clientList;
    private List<User> userList;
    private List<Driver> driverList;
    private List<Taxi> taxiList;
    private List<Ride> rideList;
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Before
    public void initTables() throws SQLException {
        userList = TableCreator.initUserTable();
        clientList = TableCreator.initClientTable();
        TableCreator.initClientTypeTable();
        driverList=TableCreator.initDriverTable();
        TableCreator.initTaxiTypeTable();
        taxiList = TableCreator.initTaxiTable();
        rideList=TableCreator.initRideTable();
    }
    @After
    public void cleanTables() throws SQLException{
        TableCleaner.cleanRideTable();
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanClientTypeTable();
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();
    }

    @Test
    public void putRideToDatabaseParameters_AddsRecordToDatabase() throws DAOException {
        Driver driver = driverList.get(0);
        Client client = clientList.get(0);
        Taxi taxi  = taxiList.get(0);
        double cost =30, distance=20;
        Date rideStart=new Date(System.currentTimeMillis());
        Date rideFinish =rideStart;
        Ride ride = new Ride(driver.getDriverId(),client.getClientId(),taxi.getTaxiId(),cost,distance,rideStart,rideFinish);
        ride.setRideId(rideList.size()+1);
        RideStatisticsService.getRideStatisticsService().putRideToDatabase(client,driver,taxi,cost,distance,rideStart,rideFinish);
        List<Ride> firstClientRides = rideList.subList(0,rideList.size()-2);
        firstClientRides.add(ride);
        assertEquals(firstClientRides, DAOFactory.getInstance().getRideDAO().findRidesForClient(client,0,firstClientRides.size()));
    }

    @Test
    public void getClientsSpentMoney_ReturnsRightValue_ForClient()throws DAOException{
        for(Client client:clientList){
            double moneySpent = rideList.stream().mapToDouble(ride -> (ride.getClientId() == client.getClientId())?ride.getCost():0.0).sum();
            assertEquals(moneySpent,RideStatisticsService.getRideStatisticsService().getClientsSpentMoney(client),0.01);
        }
    }

    @Test
    public void putRideToDatabaseRide_AddsRecordToDatabase() throws DAOException {
        Driver driver = driverList.get(0);
        Client client = clientList.get(0);
        Taxi taxi  = taxiList.get(0);
        double cost =30, distance=20;
        Date rideStart=new Date(System.currentTimeMillis());
        Date rideFinish =rideStart;
        Ride ride = new Ride(driver.getDriverId(),client.getClientId(),taxi.getTaxiId(),cost,distance,rideStart,rideFinish);
        ride.setRideId(rideList.size()+1);
        RideStatisticsService.getRideStatisticsService().putRideToDatabase(ride);
        List<Ride> firstClientRides = rideList.subList(0,rideList.size()-2);
        firstClientRides.add(ride);
        assertEquals(firstClientRides, DAOFactory.getInstance().getRideDAO().findRidesForClient(client,0,firstClientRides.size()));
    }

    @Test
    public void getClientsRides_getsAllRides() throws DAOException {
        for(Client client:clientList){
            List<Ride> clientRides =
                    rideList.stream().map(ride -> (ride.getClientId() == client.getClientId())?ride:null)
                            .filter(ride -> ride!=null).collect(Collectors.toList());
            assertEquals(clientRides, RideStatisticsService.getRideStatisticsService().getClientsRides(client,0,clientRides.size()));
        }
    }

    @Test
    public void findSize_getsAllSizes() throws DAOException {
        for(Client client:clientList){
            List<Ride> clientRides =
                    rideList.stream().map(ride -> (ride.getClientId() == client.getClientId())?ride:null)
                            .filter(ride -> ride!=null).collect(Collectors.toList());
            assertEquals(clientRides.size(), RideStatisticsService.getRideStatisticsService().getTableSize(client));
        }
    }
}
