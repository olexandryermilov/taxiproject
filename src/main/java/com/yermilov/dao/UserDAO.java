package com.yermilov.dao;

import com.yermilov.domain.User;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    UserDAO(){

    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
    private final static String SQL_SELECT_BY_LOGIN = "select * from taxisystemdb.user where email=?";
    public User findByEmail(String email) throws DAOException {
        Connection connection = null;
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_LOGIN);
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    User user = new User(resultSet.getInt("id"),email,
                            resultSet.getString("password"),resultSet.getString("name"),
                            resultSet.getString("surname"));
   
                    return user;
                }
            } catch (SQLException e){
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
            return null;
        } catch (SQLException e) {
            //log
        } finally {
            close(connection);
        }
        return null;
    }
}
