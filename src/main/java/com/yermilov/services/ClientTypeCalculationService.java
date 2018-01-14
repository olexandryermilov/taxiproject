package com.yermilov.services;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTypeCalculationService {
    private final static Logger logger = LoggerFactory.getLogger(ClientTypeCalculationService.class);
    private final static ClientTypeCalculationService COST_CALCULATION_SERVICE = new ClientTypeCalculationService();
    private IDAOFactory daoFactory;
    private ClientTypeCalculationService(){
        daoFactory=DAOFactory.getInstance();
    }
    public static ClientTypeCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }

    public int getClientsDiscount(Client client) throws DAOException {
        double moneySpent = RideStatisticsService.getRideStatisticsService().getClientsSpentMoney(client);
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        int discount = clientTypeDAO.findDiscountByMoneySpent(moneySpent);
        return discount;
    }
}
