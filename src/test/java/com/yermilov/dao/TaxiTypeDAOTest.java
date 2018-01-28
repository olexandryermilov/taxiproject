package com.yermilov.dao;

import com.yermilov.tableworkers.TableCreator;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TaxiTypeDAOTest {
    private static List<TaxiType> allTaxiTypes;
    @Before
    public void initDatabase() throws SQLException {
        allTaxiTypes= TableCreator.initTaxiTypeTable();
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @After
    public void dropTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();

        String SQL_DROP = "DROP TABLE `taxitype`";
        PreparedStatement ps = connection.prepareStatement(SQL_DROP);
        ps.execute();
    }
    //findAll
    //findById
    //create
    //update
    @Test
    public void findAll_ReturnAllTaxiTypes() throws DAOException {
        final List<TaxiType> RIGHT_ANSWER = allTaxiTypes;
        TaxiTypeDAO taxiTypeDAO = DAOFactory.getInstance().getTaxiTypeDAO();
        assertEquals(RIGHT_ANSWER,taxiTypeDAO.findAll());
    }

    @Test
    public void create_AddsTaxiTypeToDatabase() throws DAOException {
        final TaxiType taxiType = new TaxiType(3,"blackcab");
        taxiType.setTaxiTypeId(3);
        TaxiTypeDAO taxiTypeDAO = DAOFactory.getInstance().getTaxiTypeDAO();
        taxiTypeDAO.create(taxiType);
        allTaxiTypes.add(taxiType);
        assertEquals(allTaxiTypes,taxiTypeDAO.findAll());
    }

    @Test
    public void findById_ReturnsTaxi_WhenIdExists() throws DAOException {
        final TaxiType TAXITYPE = allTaxiTypes.get(0);
        TaxiTypeDAO taxiTypeDAO = DAOFactory.getInstance().getTaxiTypeDAO();
        assertEquals(TAXITYPE,taxiTypeDAO.findById(TAXITYPE.getTaxiTypeId()));
    }
    @Test
    public void findById_ReturnsNull_WhenThereIsNoTaxiWithSuchCarNumber() throws DAOException{
        final int ID = 15;
        TaxiTypeDAO taxiTypeDAO = DAOFactory.getInstance().getTaxiTypeDAO();
        assertNull(taxiTypeDAO.findById(ID));
    }
    @Test
    public void update_ChangesRecordInDatabase()throws DAOException{
        final TaxiType taxiType = new TaxiType(3,"blackcab");
        taxiType.setTaxiTypeId(2);
        TaxiTypeDAO taxiTypeDAO = DAOFactory.getInstance().getTaxiTypeDAO();
        taxiTypeDAO.update(taxiType);
        allTaxiTypes.set(1,taxiType);
        assertEquals(allTaxiTypes,taxiTypeDAO.findAll());
    }
}
