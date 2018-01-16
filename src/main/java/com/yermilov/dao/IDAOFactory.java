package com.yermilov.dao;

public interface IDAOFactory {
    ClientDAO getClientDAO();
    UserDAO getUserDAO();
    AdminDAO getAdminDAO();
    ClientTypeDAO getClientTypeDAO();
    RideDAO getRideDAO();
    TaxiDAO getTaxiDAO();
    DriverDAO getDriverDAO();
    TaxiTypeDAO getTaxiTypeDAO();
}
