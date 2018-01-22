package com.yermilov.admin.service;

import com.yermilov.dao.DAOFactory;
import com.yermilov.dao.IDAOFactory;
import com.yermilov.dao.UserDAO;
import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.tableworkers.TableCleaner;
import com.yermilov.tableworkers.TableCreator;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceTest {
    @Test
    public void getAllUsers_returnsRightData() throws DAOException {
        final User u1= new User("abc@gmail.com","abcd","Alex","Ivanov"),
                   u2= new User("bcd@gmail.com","bcde","Oleg","Vinnik"),
                   u3= new User("cde@gmail.com","cdeg","Alla","Logica");
        List<User> RIGHT_ANSWER = new ArrayList<>();
        RIGHT_ANSWER.add(u1);
        RIGHT_ANSWER.add(u2);
        RIGHT_ANSWER.add(u3);
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findLimitedAmount(0,3)).thenReturn(RIGHT_ANSWER);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        UsersService usersService = UsersService.getUsersService();
        usersService.setDaoFactory(daoFactory);
        List<User> answer = usersService.getUsers(0,3);
        assertEquals(RIGHT_ANSWER,answer);
    }
    @Test(expected = DAOException.class)
    public void getAllUsers_throwsDaoException_whenDAOthrowsDAOException() throws DAOException {
        IDAOFactory daoFactory = mock(IDAOFactory.class);
        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findLimitedAmount(0,3)).thenThrow(new DAOException("smth went wrong"));
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        UsersService usersService = UsersService.getUsersService();
        usersService.setDaoFactory(daoFactory);
        List<User> answer = usersService.getUsers(0,3);
    }
    @Test
    public void findSize_ReturnsCorrectSize() throws DAOException, SQLException {
        UsersService.getUsersService().setDaoFactory(DAOFactory.getInstance());
        List<User> allUsers = TableCreator.initUserTable();
        final int RIGHT_SIZE = 4;
        assertEquals(RIGHT_SIZE,UsersService.getUsersService().getTableSize());
        TableCleaner.cleanUserTable();
    }
    @Test
    public void findLimitedAmount_ReturnsCorrectSize() throws DAOException, SQLException {
        UsersService.getUsersService().setDaoFactory(DAOFactory.getInstance());
        List<User> allUsers = TableCreator.initUserTable();
        final int RIGHT_SIZE = 2;
        assertEquals(RIGHT_SIZE,UsersService.getUsersService().getUsers(0,2).size());
        TableCleaner.cleanUserTable();
    }

    @Test
    public void findLimitedAmount_ReturnsRightUsers_WhenSkippingSomeUsers() throws DAOException, SQLException {
        UsersService.getUsersService().setDaoFactory(DAOFactory.getInstance());
        List<User> allUsers = TableCreator.initUserTable();
        final int FROM=3, LIMIT = 1;
        assertEquals(allUsers.get(3),UsersService.getUsersService().getUsers(FROM,LIMIT).get(0));
        TableCleaner.cleanUserTable();
    }
}
