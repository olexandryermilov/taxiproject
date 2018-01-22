package com.yermilov.dao;

import com.yermilov.domain.ClientType;
import com.yermilov.domain.TaxiType;
import com.yermilov.exceptions.DAOException;
import com.yermilov.transactions.ConnectionWrapper;
import com.yermilov.transactions.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientTypeDAO extends AbstractDAO<ClientType> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientTypeDAO.class);
    private final static String SQL_FIND_ALL = "select * from clienttype";
    private final static String SQL_SELECT_DISCOUNT_BY_MONEY_SPENT = "select max(discount) from clienttype where moneyspent<=?";
    private final static String SQL_INSERT_CLIENTTYPE = "insert into clienttype(discount,name,moneyspent) values(?,?,?)";
    private final static String SQL_UPDATE_CLIENTTYPE = "update clienttype set moneyspent=?, name=?, discount=? where clienttypeid=?";
    //private final static String SQL_FIND_BY_CLIENTTYPEID = "select * from clienttype where clienttypeid=?";
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
    public List<ClientType> findAll() throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_FIND_ALL);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet rs = statement.executeQuery();
                List<ClientType> result = new ArrayList<>();
                while(rs.next()){
                    ClientType clientType = new ClientType(rs.getInt("discount"),rs.getString("name"),
                            rs.getDouble("moneyspent"));
                    clientType.setClientTypeId(rs.getInt("clienttypeid"));
                    if(clientType.getName().equals("nodiscount"))continue;
                    result.add(clientType);
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
    public boolean create(ClientType entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_INSERT_CLIENTTYPE);
                statement.setInt(1, entity.getDiscount());
                statement.setString(2, entity.getName());
                statement.setDouble(3, entity.getMoneySpent());
                LOGGER.debug("Statement to execute {}", statement.toString());
                return statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        finally {
        }
    }


    @Override
    public boolean update(ClientType entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_UPDATE_CLIENTTYPE);
                statement.setDouble(1, entity.getMoneySpent());
                statement.setString(2, entity.getName());
                statement.setInt(3,entity.getDiscount());
                statement.setInt(4,entity.getClientTypeId());
                LOGGER.debug("Statement to execute {}", statement.toString());
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException(e.getMessage());
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        finally {
        }
    }
}
