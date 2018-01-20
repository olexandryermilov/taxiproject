package com.yermilov.services;

import com.yermilov.dao.ClientTypeDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Client;
import com.yermilov.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for determining the type of discount to apply for specific client
 * @see com.yermilov.command.CostCalculationCommand
 */
public class ClientTypeCalculationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientTypeCalculationService.class);
    private final static ClientTypeCalculationService COST_CALCULATION_SERVICE = new ClientTypeCalculationService();
    private IDAOFactory daoFactory;
    private ClientTypeCalculationService(){
        daoFactory=DAOFactory.getInstance();
    }

    /**
     *
     * @return Instance of this class
     */
    public static ClientTypeCalculationService getCostCalculationService(){
        return COST_CALCULATION_SERVICE;
    }

    /**
     *
     * @param client Client for which we are calculating discount
     * @return Discount for client based on money spent before
     * @throws DAOException Re-throws DAOException from ClientTypeDAO
     * @see ClientTypeDAO#findDiscountByMoneySpent(double)
     */
    public int getClientsDiscount(Client client) throws DAOException {
        double moneySpent = RideStatisticsService.getRideStatisticsService().getClientsSpentMoney(client);
        ClientTypeDAO clientTypeDAO = daoFactory.getClientTypeDAO();
        int discount = clientTypeDAO.findDiscountByMoneySpent(moneySpent);
        return discount;
    }
}
