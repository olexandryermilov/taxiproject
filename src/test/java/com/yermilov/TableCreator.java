package com.yermilov;

import com.yermilov.domain.*;
import com.yermilov.transactions.H2ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static List<Driver> initDriverTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `driver` (\n" +
                "  `driverid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `userid` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`driverid`),\n" +
                "  UNIQUE KEY `driverid_UNIQUE` (`driverid`),\n" +
                "  CONSTRAINT `userdriverid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "insert into driver(userid) values(?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<Driver> allDrivers = new ArrayList<>();
        allDrivers.add(new Driver(1));
        allDrivers.add(new Driver(2));
        allDrivers.add(new Driver(3));
        for(Driver driver: allDrivers){
            ps.setInt(1,driver.getUserId());
            ps.execute();
            driver.setDriverId(i++);
        }
        return allDrivers;
    }
    public static List<User> initUserTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `user` (\n" +
                "  `userid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `email` varchar(100) DEFAULT NULL,\n" +
                "  `password` varchar(100) DEFAULT NULL,\n" +
                "  `phone` varchar(12) DEFAULT NULL,\n" +
                "  `name` varchar(45) DEFAULT NULL,\n" +
                "  `surname` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`userid`),\n" +
                "  UNIQUE KEY `idtaxies_UNIQUE` (`userid`),\n" +
                "  UNIQUE KEY `email_UNIQUE` (`email`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("alexivanov@gmail.com","qwerty","Alex","Ivanov"));
        allUsers.add(new User("antonsidorov@gmail.com","qwertyuiop","Anton","Sidorov"));
        allUsers.add(new User("yuriiomelyanenko@gmail.com","asmons","Yurii","Omelyanenko"));
        allUsers.add(new User("andriikoval@gmail.com","cplusplus", "Andrii", "Koval"));
        String SQL_INSERT = "insert into user(email,password,name,surname) values(?,?,?,?)";
        int i = 1;
        for(User user : allUsers){
            user.setUserId(i++);
            ps=connection.prepareStatement(SQL_INSERT);
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setString(4,user.getSurname());
            ps.execute();
        }
        return allUsers;
    }
    public static List<Admin> initAdminTable() throws SQLException{
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `admin` (\n" +
                "  `adminid` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `email` VARCHAR(45) NULL,\n" +
                "  `password` VARCHAR(45) NULL,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `surname` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`adminid`),\n" +
                "  UNIQUE INDEX `adminid_UNIQUE` (`adminid` ASC),\n" +
                "  UNIQUE INDEX `email_UNIQUE` (`email` ASC));";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "insert into admin(email,password,name,surname) values(?,?,?,?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<Admin> allAdmins = new ArrayList<>();
        allAdmins.add(new Admin("olexandryermilov@gmail.com","olyer","Olexandr","Yermilov"));
        for(Admin admin:allAdmins){
            ps.setString(1,admin.getEmail());
            ps.setString(2,admin.getPassword());
            ps.setString(3,admin.getName());
            ps.setString(4,admin.getSurname());
            ps.execute();
            admin.setAdminId(i++);
        }
        return allAdmins;
    }

    public static List<Client> initClientTable() throws SQLException{
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `client` (\n"+
                "  `clientid` int(11) NOT NULL AUTO_INCREMENT,\n"+
                "  `userid` int(11) NOT NULL,\n"+
                "  PRIMARY KEY (`clientid`),\n"+
                "  UNIQUE KEY `clientid_UNIQUE` (`clientid`),\n"+
                "  UNIQUE KEY `userid_UNIQUE` (`userid`),\n"+
                "  CONSTRAINT `usersClientid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION\n"+
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "insert into client(userid) values(?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<Client> allClients = new ArrayList<>();
        allClients.add(new Client(1));
        allClients.add(new Client(2));
        allClients.add(new Client(3));
        for(Client client: allClients){
            ps.setInt(1,client.getUserId());
            ps.execute();
            client.setClientId(i++);
        }
        return allClients;
    }

    public static List<ClientType> initClientTypeTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `clienttype` (\n" +
                "  `clienttypeid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) DEFAULT NULL,\n" +
                "  `discount` int(11) DEFAULT NULL,\n" +
                "  `moneyspent` decimal(10,0) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`clienttypeid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "insert into clienttype(discount,name,moneyspent) values(?,?,?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<ClientType> allClientTypes = new ArrayList<>();
        allClientTypes.add(new ClientType(0,"nodiscount",-1));
        allClientTypes.add(new ClientType(3,"newbie",1000.0));
        allClientTypes.add(new ClientType(5,"friend",5000.0));
        allClientTypes.add(new ClientType(10,"bff",10000.0));
        for(ClientType clientType:allClientTypes){
            ps.setInt(1,clientType.getDiscount());
            ps.setString(2,clientType.getName());
            ps.setDouble(3,clientType.getMoneySpent());
            ps.execute();
            clientType.setClientTypeId(i++);
        }
        return allClientTypes;
    }
    public static List<TaxiType> initTaxiTypeTable() throws SQLException{
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `taxitype` (\n" +
                "  `taxitypeid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `taxitypename` varchar(45) DEFAULT NULL,\n" +
                "  `fare` decimal(11,0) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`taxitypeid`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=3";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "insert into taxitype(taxitypename,fare) values(?,?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<TaxiType> allTaxiTypes = new ArrayList<>();
        allTaxiTypes.add(new TaxiType(1.0,"default"));
        allTaxiTypes.add(new TaxiType(2.0,"elite"));
        for(TaxiType taxiType: allTaxiTypes){
            ps.setString(1,taxiType.getTaxiTypeName());
            ps.setDouble(2,taxiType.getFare());
            ps.execute();
            taxiType.setTaxiTypeId(i++);
        }
        return allTaxiTypes;
    }

    public static List<Taxi> initTaxiTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `taxi` (\n" +
                "  `taxiid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `taxitypeid` int(11) DEFAULT NULL,\n" +
                "  `carnumber` varchar(45) DEFAULT NULL,\n" +
                "  `driverid` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`taxiid`),\n" +
                "  UNIQUE KEY `taxiid_UNIQUE` (`taxiid`),\n" +
                "  CONSTRAINT `driverid` FOREIGN KEY (`driverid`) REFERENCES `driver` (`driverid`) ON DELETE CASCADE ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `taxitypeid` FOREIGN KEY (`taxitypeid`) REFERENCES `taxitype` (`taxitypeid`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "insert into taxi(taxitypeid,carnumber,driverid) values(?,?,?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<Taxi> allTaxies=new ArrayList<>();
        allTaxies.add(new Taxi(1,1,"AA1234BA"));
        allTaxies.add(new Taxi(2,1,"AB3412BA"));
        allTaxies.add(new Taxi(2,2,"AO4521AA"));
        allTaxies.add(new Taxi(3,2,"AK2333AA"));
        for(Taxi taxi:allTaxies){
            ps.setInt(1,taxi.getTaxiTypeId());
            ps.setString(2,taxi.getCarNumber());
            ps.setInt(3,taxi.getDriverId());
            ps.execute();
            taxi.setTaxiId(i++);
        }
        return allTaxies;
    }
    public static List<Ride> initRideTable() throws SQLException{
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `ride` (\n" +
                "  `rideid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `driverid` int(11) DEFAULT NULL,\n" +
                "  `clientid` int(11) DEFAULT NULL,\n" +
                "  `distance` decimal(10,0) DEFAULT NULL,\n" +
                "  `ridestart` datetime DEFAULT NULL,\n" +
                "  `ridefinish` datetime DEFAULT NULL,\n" +
                "  `cost` decimal(10,0) DEFAULT NULL,\n" +
                "  `taxiid` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`rideid`),\n" +
                "  CONSTRAINT `clientsid` FOREIGN KEY (`clientid`) REFERENCES `client` (`clientid`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `driversid` FOREIGN KEY (`driverid`) REFERENCES `driver` (`driverid`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `taxisid` FOREIGN KEY (`taxiid`) REFERENCES `taxi` (`taxiid`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;\n" +
                "\n";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        String SQL_INSERT = "INSERT INTO ride(driverId,clientId,taxiId,cost,distance,rideStart,rideFinish)"+
                "VALUES(?,?,?,?,?,?,?)";
        ps=connection.prepareStatement(SQL_INSERT);
        int i = 1;
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(1,1,1,38.0,27.0,
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        rides.add(new Ride(2,1,2,48.0,17.0,
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        rides.add(new Ride(3,1,4,38.0,17.0,
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        rides.add(new Ride(3,2,4,38.0,17.0,
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));

        for(Ride ride: rides){
            ps.setInt(1,ride.getDriverId());
            ps.setInt(2,ride.getClientId());
            ps.setInt(3,ride.getTaxiId());
            ps.setDouble(4,ride.getCost());
            ps.setDouble(5,ride.getDistance());
            ps.setDate(6,ride.getRideStart());
            ps.setDate(7,ride.getRideFinish());
            ps.execute();
            ride.setRideId(i++);
        }
        return rides;
    }
}
