package com.yermilov.admin.service;

import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.services.LoginService;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class LoginServiceTest {
    @Test
    public void getUser_returnsUser_whenRightData() throws DAOException {
        final String TEST_EMAIL="abs@gmail.com",TEST_PASSWORD="qweerty";
        final User RIGHT_ANSWER = new User(TEST_EMAIL,TEST_PASSWORD,null,null);
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(RIGHT_ANSWER);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        LoginService loginService = LoginService.getLoginService();
        loginService.setDaoFactory(daoFactory);
        User answer = loginService.getUser(TEST_EMAIL,TEST_PASSWORD);
        assertEquals(RIGHT_ANSWER,answer);
    }
    @Test
    public void getUser_returnsNull_whenBadData() throws DAOException {
        final String TEST_EMAIL="abs@gmail.com",TEST_PASSWORD="qweerty";
        final User RIGHT_ANSWER = new User(TEST_EMAIL,TEST_PASSWORD,null,null);
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(RIGHT_ANSWER);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        LoginService loginService = LoginService.getLoginService();
        loginService.setDaoFactory(daoFactory);
        User answer = loginService.getUser(TEST_EMAIL,TEST_PASSWORD+"1");
        assertNull(answer);
    }
}
