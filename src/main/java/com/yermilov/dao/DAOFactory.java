package com.yermilov.dao;

public class DAOFactory {
    private final static DAOFactory daoFactory = new DAOFactory();
    private DAOFactory(){}
    public static DAOFactory getInstance() {
        return daoFactory;
    }
    private final static UserDAO userDAO= new UserDAO();
    public static UserDAO getUserDAO(){
        return userDAO;
    }
    private final static AdminDAO adminDAO= new AdminDAO();
    public static AdminDAO getAdminDAO(){
        return adminDAO;
    }
}
