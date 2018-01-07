package com.yermilov.dao;

import com.yermilov.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <T>{
    public abstract List<T> findAll () throws DAOException;
    public abstract T findById (int id)throws DAOException;
    public abstract boolean delete(int id)throws DAOException;
    public abstract boolean delete(T entity)throws DAOException;
    public abstract boolean create(T entity)throws DAOException;
    public abstract T update(T entity)throws DAOException;
    public void close(Statement statement){
        try{
            if(statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            //log
        }
    }
    public void close(Connection connection){
        try{
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            //log
        }
    }
}
