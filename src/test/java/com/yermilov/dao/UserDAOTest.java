package com.yermilov.dao;

import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.H2ConnectionPool;
import com.yermilov.transactions.TransactionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDAOTest {
    private static List<User> allUsers;
    @Before
    public void initDatabase() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_CREATE_DATABASE = "CREATE TABLE `user` (\n" +
                "  `userid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `email` varchar(100) DEFAULT NULL,\n" +
                "  `password` varchar(100) DEFAULT NULL,\n" +
                "  `phone` varchar(12) DEFAULT NULL,\n" +
                "  `name` varchar(45) DEFAULT NULL,\n" +
                "  `surname` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`userid`),\n" +
                "  UNIQUE KEY `idtaxies_UNIQUE` (`userid`),\n" +
                "  UNIQUE KEY `email_UNIQUE` (`email`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_DATABASE);
        ps.execute();
        allUsers = new ArrayList<>();
        allUsers.add(new User("alexivanov@gmail.com","qwerty","Alex","Ivanov"));
        allUsers.add(new User("antonsidorov@gmail.com","qwertyuiop","Anton","Sidorov"));
        allUsers.add(new User("yuriiomelyanenko@gmail.com","asmons","Yurii","Omelyanenko"));
        allUsers.add(new User("andriikoval@gmail.com","cplusplus", "Andrii", "Koval"));
        String SQL_INSERT = "insert into user(email,password,name,surname) values(?,?,?,?)";
        int i = 1;
        for(User user : allUsers){
            user.setUserId(i++);
            ps=connection.prepareStatement(SQL_INSERT);
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setString(4,user.getSurname());
            ps.execute();
        }
        TransactionManager.setConnectionPool(H2ConnectionPool.getInstance());
    }
    @After
    public void dropTable() throws SQLException {
        Connection connection = H2ConnectionPool.getInstance().getConnection();
        String SQL_DROP_DATABASE = "DROP TABLE `user`";
        PreparedStatement ps = connection.prepareStatement(SQL_DROP_DATABASE);
        ps.execute();
    }
    @Test
    public void findSize_ReturnsCorrectSize() throws DAOException {
        final int RIGHT_SIZE = 4;
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        assertEquals(RIGHT_SIZE,userDAO.findSize());
    }
    @Test
    public void findLimitedAmount_ReturnsCorrectSize() throws DAOException{
        final int RIGHT_SIZE = 2;
        UserDAO userDAO=DAOFactory.getInstance().getUserDAO();
        assertEquals(RIGHT_SIZE,userDAO.findLimitedAmount(0,2).size());
    }

    @Test
    public void findLimitedAmount_ReturnsRightUsers_WhenSkippingSomeUsers() throws DAOException {
        final int FROM=3, LIMIT = 1;
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        assertEquals(allUsers.get(3),userDAO.findLimitedAmount(FROM,LIMIT).get(0));
    }
    @Test
    public void createsUser() throws DAOException {
        final String EMAIL = "olyer@gmail.com";
        final User user = new User(EMAIL,"olyer15","olexandr", "yermilov");

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userDAO.create(user);
        User userFromDB = userDAO.findByEmail(EMAIL);
        user.setUserId(userFromDB.getUserId());
        assertEquals(user,userFromDB);
    }
    @Test
    public void deletesUser() throws DAOException {
        final String EMAIL = "olyer@gmail.com";
        final User user = new User(EMAIL,"olyer15","olexandr", "yermilov");
        final int RIGHT_SIZE = 4;

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userDAO.create(user);
        user.setUserId(userDAO.findByEmail(EMAIL).getUserId());
        userDAO.delete(user.getUserId());
        assertEquals(RIGHT_SIZE, userDAO.findSize());
    }
    @Test
    public void findByEmail_ReturnsNull_WhenThereIsNoSuchUser() throws DAOException {
        final String EMAIL = "olyer@gmail.com";
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        assertNull(userDAO.findByEmail(EMAIL));
    }
}
