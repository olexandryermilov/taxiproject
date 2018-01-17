package com.yermilov.dao;

import com.yermilov.domain.TaxiType;
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

public class TaxiTypeDAO extends AbstractDAO<TaxiType> {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaxiTypeDAO.class);
    private final static String SQL_FIND_ALL = "select * from taxitype";
    @Override
    public List<TaxiType> findAll() throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_FIND_ALL);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet rs = statement.executeQuery();
                List<TaxiType> result = new ArrayList<>();
                while(rs.next()){
                    TaxiType taxiType = new TaxiType(rs.getDouble("fare"),rs.getString("taxitypename"));
                    taxiType.setTaxiTypeId(rs.getInt("taxitypeid"));
                    result.add(taxiType);
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

    private final static String SQL_SELECT_BY_ID = "select * from taxitype where taxitypeid=?";
    @Override
    public TaxiType findById(int id) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_SELECT_BY_ID);
                statement.setInt(1, id);
                LOGGER.debug("Statement to execute {}",statement.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    TaxiType taxiType = new TaxiType(resultSet.getDouble("fare"),
                            resultSet.getString("taxitypename"));
                    taxiType.setTaxiTypeId(id);
                    return taxiType;
                }
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
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(TaxiType entity) {
        return false;
    }

    private final static String SQL_INSERT_TAXITYPE = "insert into taxitype(fare,taxitypename) values (?,?)";
    @Override
    public boolean create(TaxiType entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_INSERT_TAXITYPE);
                statement.setDouble(1, entity.getFare());
                statement.setString(2, entity.getTaxiTypeName());
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

    private final static String SQL_UPDATE_TAXITYPE = "update taxitype set fare=?, taxitypename=? where taxitypeid=?";
    @Override
    public boolean update(TaxiType entity) throws DAOException {
        try {
            ConnectionWrapper con = TransactionManager.getConnection();
            try {
                PreparedStatement statement = con.preparedStatement(SQL_UPDATE_TAXITYPE);
                statement.setDouble(1, entity.getFare());
                statement.setString(2, entity.getTaxiTypeName());
                statement.setInt(3,entity.getTaxiTypeId());
                LOGGER.debug("Statement to execute {}", statement.toString());
                statement.execute();
                return false;
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
