package com.yermilov.admin.service;

import com.yermilov.dao.AdminDAO;
import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    @BeforeClass
    public static void changeDatabaseConnector(){
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @Test
    public void getAdmin_returnsAdmin_whenRightDat_MockingDAO() throws DAOException {
        final String TEST_EMAIL="abs@gmail.com",TEST_PASSWORD="qweerty";
        final Admin RIGHT_ANSWER = new Admin(TEST_EMAIL,TEST_PASSWORD,null,null);
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        AdminDAO adminDAO = mock(AdminDAO.class);
        when(adminDAO.findByEmail(TEST_EMAIL)).thenReturn(RIGHT_ANSWER);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAO);
        LoginService loginService = LoginService.getLoginService();
        loginService.setDaoFactory(daoFactory);
        Admin answer = loginService.getAdmin(TEST_EMAIL,TEST_PASSWORD);
        assertEquals(RIGHT_ANSWER,answer);
    }
    @Test
    public void getAdmin_returnsNull_whenBadData_MockingDAO() throws DAOException {
        final String TEST_EMAIL="abs@gmail.com",TEST_PASSWORD="qweerty";
        final Admin RIGHT_ANSWER = new Admin(TEST_EMAIL,TEST_PASSWORD,null,null);
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        AdminDAO adminDAO = mock(AdminDAO.class);
        when(adminDAO.findByEmail(TEST_EMAIL)).thenReturn(RIGHT_ANSWER);
        when(daoFactory.getAdminDAO()).thenReturn(adminDAO);
        LoginService loginService = LoginService.getLoginService();
        loginService.setDaoFactory(daoFactory);
        Admin answer = loginService.getAdmin(TEST_EMAIL,TEST_PASSWORD+"1");
        assertNull(answer);
    }

    @Test
    public void getAdmin_returnsAdmin_WhenRightData() throws SQLException, DAOException {
        List<Admin> allAdmins = TableCreator.initAdminTable();
        LoginService loginService = LoginService.getLoginService();
        loginService.setDaoFactory(DAOFactory.getInstance());
        final Admin ADMIN = allAdmins.get(0);
        assertEquals(ADMIN,loginService.getAdmin(ADMIN.getEmail(),ADMIN.getPassword()));
        TableCleaner.cleanAdminTable();
    }

    @Test
    public void getAdmin_returnsNull_WhenBadPassword() throws SQLException, DAOException {
        List<Admin> allAdmins = TableCreator.initAdminTable();
        LoginService loginService = LoginService.getLoginService();
        final Admin ADMIN = allAdmins.get(0);
        assertNull(loginService.getAdmin(ADMIN.getEmail(),ADMIN.getPassword()+"1"));
        TableCleaner.cleanAdminTable();
    }

    @Test
    public void getAdmin_returnsNull_WhenBadEmail() throws SQLException, DAOException {
        List<Admin> allAdmins = TableCreator.initAdminTable();
        LoginService loginService = LoginService.getLoginService();
        final Admin ADMIN = allAdmins.get(0);
        assertNull(loginService.getAdmin(ADMIN.getEmail()+"1",ADMIN.getPassword()));
        TableCleaner.cleanAdminTable();
    }
}
