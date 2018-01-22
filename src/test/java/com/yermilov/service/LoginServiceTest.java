package com.yermilov.service;

import com.yermilov.admin.service.LoginService;
import com.yermilov.dao.AdminDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    @Test
    public void getAdmin_returnsAdmin_whenRightData() throws DAOException {
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
    public void getAdmin_returnsNull_whenBadData() throws DAOException {
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
}
