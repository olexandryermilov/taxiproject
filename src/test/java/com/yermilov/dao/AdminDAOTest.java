package com.yermilov.dao;

import com.yermilov.TableCreator;
import com.yermilov.domain.Admin;
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

public class AdminDAOTest {
    private static List<Admin> allAdmins;
    @Before
    public void initDatabase() throws SQLException {
        allAdmins = TableCreator.initAdminTable();
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @After
    public void dropTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_DROP_DATABASE = "DROP TABLE `admin`";
        PreparedStatement ps = connection.prepareStatement(SQL_DROP_DATABASE);
        ps.execute();
    }
    @Test
    public void findByEmail_returnsRightAdmin_WhenRightEmail() throws DAOException {
        final String EMAIL = "olexandryermilov@gmail.com";
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        assertEquals(allAdmins.get(0),adminDAO.findByEmail(EMAIL));
    }
    @Test
    public void findByEmail_returnsNull_WhenWrongEmail() throws DAOException {
        final String EMAIL = "olexandr1yermilov@gmail.com";
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        assertNull(adminDAO.findByEmail(EMAIL));
    }
}
