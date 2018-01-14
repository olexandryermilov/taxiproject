package com.yermilov.dao;

import com.yermilov.domain.Admin;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO extends AbstractDAO<Admin> {
    AdminDAO(){

    }
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminDAO.class);
    @Override
    public List<Admin> findAll() throws DAOException {
        return null;
    }

    @Override
    public Admin findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Admin entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Admin entity) throws DAOException {
        return false;
    }

    @Override
    public Admin update(Admin entity) throws DAOException {
        return null;
    }

    private final static String SQL_SELECT_BY_LOGIN = "select * from taxisystemdb.admin where email=?";
    public Admin findByEmail(String email) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_LOGIN);
                statement.setString(1, email);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Admin admin = new Admin(email,
                            resultSet.getString("password"),resultSet.getString("name"),
                            resultSet.getString("surname"));
                    return admin;
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
        } finally {
        }
        return null;
    }
}
