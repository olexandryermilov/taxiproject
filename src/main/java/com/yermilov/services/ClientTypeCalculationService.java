package com.yermilov.services;

import com.yermilov.dao.ClientDAO;
import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTypeCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(ClientTypeCalculationService.class);
    private final static ClientTypeCalculationService COST_CALCULATION_SERVICE = new ClientTypeCalculationService();
    private ClientTypeCalculationService(){

    }
    public static ClientTypeCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }

    public int getClientsDiscount(int userId) throws DAOException{
        ClientDAO clientDAO = DAOFactory.getClientDAO();
        Client client = clientDAO.findClientByUserId(userId);
        return getClientsDiscount(client);
    }
    public int getClientsDiscount(Client client) throws DAOException {
        double moneySpent = RideStatisticsService.getRideStatisticsService().getClientsSpentMoney(client);
        ClientTypeDAO clientTypeDAO = DAOFactory.getClientTypeDAO();
        int discount = clientTypeDAO.findDiscountByMoneySpent(moneySpent);
        return discount;
    }
}
