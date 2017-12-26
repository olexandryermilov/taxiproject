package com.taxisystem.dao;

import com.taxisystem.domain.Client;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {
    private static final String SQL_SELECT_ALL = "select * from taxisystemdb.client";
    private final static String SQL_SELECT_BY_ID = "select * from taxisystemdb.client where clientid=?";
    private final static String SQL_DELETE_BY_ID = "delete from taxisystemdb.client where clientid=?";
    private static final String SQL_INSERT_CLIENT="insert into taxisystemdb.client(userid) values(?)";
    private BasicDataSource basicDataSource;
    public ClientDAO(BasicDataSource basicDataSource){
        this.basicDataSource=basicDataSource;
    }
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                Client client = new Client(resultSet.getInt("clientid"),resultSet.getInt("userid"));
                clients.add(client);
            }
        } catch (SQLException e) {
            //log
        }
        finally {
            close(connection);
        }
        return clients;
    }


    @Override
    public Client findById(int id) {
        Connection connection = null;
        try{
            connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            Client client = new Client(id,rs.getInt("userid"));
            return client;
        } catch (SQLException e) {
            //log
        }
        finally {
            close(connection);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try{
            connection = basicDataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setInt(1,id);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            //log
        }
        finally {
            close(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Client entity) {
        Connection connection = null;
        try{
            connection = basicDataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setInt(1,entity.getClientId());
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            //log
        }
        finally {
            close(connection);
        }
        return false;
    }


    @Override
    public boolean create(Client entity) {
        Connection connection = null;
        try{
            connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL_INSERT_CLIENT);
            preparedStatement.setInt(1,entity.getUserId());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            //log
        }
        finally {
            close(connection);
        }
        return false;
    }

    @Override
    public Client update(Client entity) {
        throw new UnsupportedOperationException();
    }

}
