package com.yermilov.unit.service;

import com.yermilov.dao.ClientDAO;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.Client;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.exceptions.RegistrationException;
import com.yermilov.exceptions.TransactionException;
import com.yermilov.services.RegistrationService;
import org.junit.Test;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationServiceTest {
    @Test(expected = RegistrationException.class)
    public void register_throwsException_whenEmailOccupied() throws DAOException, TransactionException, RegistrationException, SQLException {
        final String TEST_EMAIL = "myemail@gmail.com";
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(new User(TEST_EMAIL,null,null,null));
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        RegistrationService.getRegistrationService().setDaoFactory(daoFactory);
        RegistrationService.getRegistrationService().register(TEST_EMAIL,"","","");
    }
    @Test(expected = RegistrationException.class)
    public void register_throwsRegistrationException_WhenClientDAOFails() throws DAOException, TransactionException, RegistrationException, SQLException {
        final String TEST_EMAIL = "myemail@gmail.com";
        final int TEST_USER_ID = 1;
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        User user = new User(TEST_EMAIL,"","","");
        user.setUserId(TEST_USER_ID);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(null,user);
        ClientDAO clientDAO = mock(ClientDAO.class);
        when(clientDAO.create(any(Client.class))).thenReturn(false);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getClientDAO()).thenReturn(clientDAO);
        RegistrationService.getRegistrationService().setDaoFactory(daoFactory);
        RegistrationService.getRegistrationService().register(TEST_EMAIL,"","","");
    }
    @Test(expected = RegistrationException.class)
    public void register_throwsRegistrationException_WhenUserDAOFails() throws DAOException, TransactionException, RegistrationException, SQLException {
        final String TEST_EMAIL = "myemail@gmail.com";
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(null);
        when(userDAO.create(any(User.class))).thenReturn(false);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        RegistrationService.getRegistrationService().setDaoFactory(daoFactory);
        RegistrationService.getRegistrationService().register(TEST_EMAIL,"","","");
    }
    @Test(expected = RegistrationException.class)
    public void register_throwsRegistrationException_WhenUserDAOThrowsRegistrationException() throws DAOException, TransactionException, RegistrationException, SQLException {
        final String TEST_EMAIL = "myemail@gmail.com";
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(null);
        when(userDAO.create(any(User.class))).thenThrow(new DAOException("smth went wrong"));
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        RegistrationService.getRegistrationService().setDaoFactory(daoFactory);
        RegistrationService.getRegistrationService().register(TEST_EMAIL,"","","");
    }
    @Test(expected = RegistrationException.class)
    public void register_throwsRegistrationException_WhenClientDAOThrowsRegistrationException() throws DAOException, TransactionException, RegistrationException, SQLException {
        final String TEST_EMAIL = "myemail@gmail.com";
        final int TEST_USER_ID = 1;
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        User user = new User(TEST_EMAIL,"","","");
        user.setUserId(TEST_USER_ID);
        when(userDAO.findByEmail(TEST_EMAIL)).thenReturn(null,user);
        ClientDAO clientDAO = mock(ClientDAO.class);
        when(clientDAO.create(any(Client.class))).thenThrow(new DAOException("smth went wrong"));
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getClientDAO()).thenReturn(clientDAO);
        RegistrationService.getRegistrationService().setDaoFactory(daoFactory);
        RegistrationService.getRegistrationService().register(TEST_EMAIL,"","","");
    }
}
