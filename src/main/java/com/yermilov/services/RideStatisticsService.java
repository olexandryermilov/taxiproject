package com.yermilov.services;

import com.yermilov.dao.ClientDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.RideDAO;
import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RideStatisticsService {
    private final static Logger logger = LoggerFactory.getLogger(RideStatisticsService.class);
    private final static RideStatisticsService RIDE_STATISTICS_SERVICE = new RideStatisticsService();
    private RideStatisticsService(){

    }
    public static RideStatisticsService getRideStatisticsService(){
        return RIDE_STATISTICS_SERVICE;
    }

    public double getClientsSpentMoney(Client client) throws DAOException {
        RideDAO rideDAO = DAOFactory.getRideDAO();
        double moneySpent = rideDAO.getMoneySpentForClient(client);//todo:implement!
        return moneySpent;
    }
}
