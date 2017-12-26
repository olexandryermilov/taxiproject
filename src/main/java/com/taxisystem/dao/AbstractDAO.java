package com.taxisystem.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <T>{
    public abstract List<T> findAll();
    public abstract T findById(int id);
    public abstract boolean delete(int id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
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
