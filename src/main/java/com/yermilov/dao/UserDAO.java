package com.yermilov.dao;

import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
    UserDAO(){

    }
    private final static String SQL_FIND_ALL = "select * from taxisystemdb.user";
    @Override
    public List<User> findAll() throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_FIND_ALL);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet rs = statement.executeQuery();
                List<User> result = new ArrayList<>();
                while(rs.next()){
                    User user = new User(rs.getString("email"),null,
                            rs.getString("name"),
                            rs.getString("surname"));
                    user.setUserId(rs.getInt("userid"));
                    result.add(user);
                }
                return result;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }
    private final static String SQL_FIND_SIZE = "select count(*) from user ";
    public int findSize() throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_FIND_SIZE);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet rs = statement.executeQuery();
                int size=0;
                if(rs.next()) {
                    size = rs.getInt(1);
                }
                return size;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }
    private final static String SQL_FIND_LIMITED_AMOUNT = "select * from user limit ?, ?";
    public List<User> findLimitedAmount(int from, int limit) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_FIND_LIMITED_AMOUNT);
                statement.setInt(1,from);
                statement.setInt(2,limit);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet rs = statement.executeQuery();
                List<User> result = new ArrayList<>();
                while(rs.next()){
                    User user = new User(rs.getString("email"),null,
                            rs.getString("name"),
                            rs.getString("surname"));
                    user.setUserId(rs.getInt("userid"));
                    result.add(user);
                }
                return result;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    private final static String SQL_DELETE_BY_ID = "delete from taxisystemdb.user where userid=?";
    @Override
    public boolean delete(int id) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_DELETE_BY_ID);
                statement.setInt(1,id);
                LOGGER.debug("Statement to execute {}",statement.toString());
                return statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        return false;
    }

    private final static String SQL_INSERT ="insert into taxisystemdb.user(email,password,name,surname) values(?,?,?,?)";
    @Override
    public boolean create(User entity)throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_INSERT);
                statement.setString(1, entity.getEmail());
                statement.setString(2, entity.getPassword());
                statement.setString(3, entity.getName());
                statement.setString(4, entity.getSurname());
                LOGGER.debug("Statement to execute {}",statement.toString());
                statement.execute();
                return true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }


    @Override
    public boolean update(User entity) throws DAOException {
        return false;
    }
    private final static String SQL_SELECT_BY_LOGIN = "select * from taxisystemdb.user where email=?";
    public User findByEmail(String email) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_LOGIN);
                statement.setString(1, email);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    User user = new User(email,
                            resultSet.getString("password"),resultSet.getString("name"),
                            resultSet.getString("surname"));
                    user.setUserId(resultSet.getInt("userid"));
                    return user;
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
    }
}
