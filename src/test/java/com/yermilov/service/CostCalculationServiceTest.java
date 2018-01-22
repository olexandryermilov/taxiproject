package com.yermilov.service;


import com.yermilov.domain.Client;
import com.yermilov.domain.Taxi;
import com.yermilov.domain.TaxiType;
import com.yermilov.domain.User;
import com.yermilov.exceptions.CostCalculationException;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.CostCalculationService;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CostCalculationServiceTest {

    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void getClient_ReturnsClient_WhenRightData() throws SQLException, DAOException {
        List<User> userList = TableCreator.initUserTable();
        List<Client> clientList = TableCreator.initClientTable();
        assertEquals(clientList.get(0), CostCalculationService.getCostCalculationService().getClient(userList.get(0).getUserId()));
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();
    }
    @Test
    public void getClient_ReturnsNull_WhenBadData() throws SQLException, DAOException {
        List<User> userList = TableCreator.initUserTable();
        List<Client> clientList = TableCreator.initClientTable();
        assertNull(CostCalculationService.getCostCalculationService().getClient(userList.size()+2));
        TableCleaner.cleanClientTable();
        TableCleaner.cleanUserTable();
    }

    @Test
    public void getFare_GetsFareRight() throws SQLException, CostCalculationException, DAOException {
        List<TaxiType> taxiTypes = TableCreator.initTaxiTypeTable();
        assertEquals(taxiTypes.get(0).getFare(),
                CostCalculationService.getCostCalculationService().getFare(taxiTypes.get(0).getTaxiTypeId()),0.01);
        TableCleaner.cleanTaxiTypeTable();
    }

    @Test
    public void getDriveCost_CalculatesRight_WithoutDiscount() throws SQLException, CostCalculationException, DAOException {
        TableCreator.initUserTable();
        List<TaxiType> taxiTypes = TableCreator.initTaxiTypeTable();
        TableCreator.initDriverTable();
        List<Taxi> taxies = TableCreator.initTaxiTable();
        assertEquals(20,CostCalculationService.getCostCalculationService().getDriveCost(taxies.get(0),10,0),
                0.01);
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }
    @Test
    public void getDriveCost_CalculatesRight_WithDiscount() throws SQLException, CostCalculationException, DAOException {
        TableCreator.initUserTable();
        List<TaxiType> taxiTypes = TableCreator.initTaxiTypeTable();
        TableCreator.initDriverTable();
        List<Taxi> taxies = TableCreator.initTaxiTable();
        assertEquals(18,CostCalculationService.getCostCalculationService().getDriveCost(taxies.get(0),10,10),
                0.01);
        TableCleaner.cleanTaxiTable();
        TableCleaner.cleanTaxiTypeTable();
        TableCleaner.cleanDriverTable();
        TableCleaner.cleanUserTable();
    }
}
