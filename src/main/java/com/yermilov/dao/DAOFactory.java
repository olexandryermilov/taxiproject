package com.yermilov.dao;

public class DAOFactory implements IDAOFactory {
    private final static DAOFactory daoFactory = new DAOFactory();
    private DAOFactory(){}
    public static DAOFactory getInstance() {
        return daoFactory;
    }
    private final static ClientDAO CLIENT_DAO = new ClientDAO();
    public ClientDAO getClientDAO(){
        return CLIENT_DAO;
    }
    private final static UserDAO userDAO= new UserDAO();
    public UserDAO getUserDAO(){
        return userDAO;
    }
    private final static AdminDAO adminDAO= new AdminDAO();
    public AdminDAO getAdminDAO(){
        return adminDAO;
    }

    private final static ClientTypeDAO CLIENT_TYPE_DAO = new ClientTypeDAO();
    public ClientTypeDAO getClientTypeDAO(){
        return CLIENT_TYPE_DAO;
    }
    private final static RideDAO RIDE_DAO = new RideDAO();
    public RideDAO getRideDAO(){
        return RIDE_DAO;
    }
    private final static TaxiDAO TAXI_DAO = new TaxiDAO();
    public TaxiDAO getTaxiDAO(){
        return TAXI_DAO;
    }
    private final static DriverDAO DRIVER_DAO = new DriverDAO();
    public DriverDAO getDriverDAO(){
        return DRIVER_DAO;
    }
}
