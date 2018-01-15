package com.yermilov.services;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.TaxiDAO;
import com.yermilov.domain.Taxi;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

public class GetCarService {
    private final static Logger logger = LoggerFactory.getLogger(GetCarService.class);
    private final static GetCarService getCarService = new GetCarService();
    private final static int CAR_NUMBER=1;
    private IDAOFactory daoFactory;
    private GetCarService(){
        daoFactory=DAOFactory.getInstance();
    }
    public static GetCarService getGetCarService(){
        return getCarService;
    }
    public Taxi getCar()throws DAOException{
        int id = new Random(new Date().getTime()).nextInt(CAR_NUMBER)+1;
        TaxiDAO taxiDAO =daoFactory.getTaxiDAO();
        Taxi taxi = taxiDAO.findById(id);
        return taxi;
    }
    public void setDaoFactory(IDAOFactory daoFactory){
        this.daoFactory=daoFactory;
    }
}
