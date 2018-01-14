package com.yermilov.dao;

import com.yermilov.domain.ClientType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientTypeDAO extends AbstractDAO<ClientType> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientTypeDAO.class);
    private static final String SQL_SELECT_ALL = "select * from taxisystemdb.clienttype";
    private final static String SQL_SELECT_BY_ID = "select * from taxisystemdb.clienttype where clienttypeid=?";
    private final static String SQL_DELETE_BY_ID = "delete from taxisystemdb.clienttype where clienttypeid=?";
    private static final String SQL_INSERT_CLIENT="insert into taxisystemdb.clienttype(userid) values(?)";

    private final static String SQL_SELECT_DISCOUNT_BY_MONEY_SPENT = "select max(discount) from clienttype where moneyspent<?";
    public int findDiscountByMoneySpent(double moneySpent) throws DAOException{
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_DISCOUNT_BY_MONEY_SPENT);
                statement.setDouble(1, moneySpent);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return resultSet.getInt("max(discount)");
                }
                return 0;

            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
        }
    }
    @Override
    public List<ClientType> findAll() {
        return null;
    }

    @Override
    public ClientType findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(ClientType entity) {
        return false;
    }

    @Override
    public boolean create(ClientType entity) {
        return false;
    }

    @Override
    public ClientType update(ClientType entity) {
        return null;
    }
}
