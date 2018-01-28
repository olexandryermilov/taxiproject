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

public class AdminDAO {
    AdminDAO(){

    }
    private final static Logger LOGGER = LoggerFactory.getLogger(AdminDAO.class);
    private final static String SQL_SELECT_BY_LOGIN = "select * from admin where email=?";
    public Admin findByEmail(String email) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            PreparedStatement statement=null;
            ResultSet resultSet=null;
            try {
                statement = con.preparedStatement(SQL_SELECT_BY_LOGIN);
                statement.setString(1, email);
                LOGGER.debug("Statement to execute {}",statement.toString());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Admin admin = new Admin(email,
                            resultSet.getString("password"),resultSet.getString("name"),
                            resultSet.getString("surname"));
                    admin.setAdminId(resultSet.getInt("adminid"));

                    return admin;
                }
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                if(resultSet!=null)resultSet.close();
                if(statement!=null)statement.close();
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
