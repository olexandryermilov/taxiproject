package com.yermilov.dao;

import com.yermilov.exceptions.DAOException;

import java.util.List;

public abstract class AbstractDAO <T>{
    public abstract List<T> findAll () throws DAOException;
    public abstract T findById (int id)throws DAOException;
    public abstract boolean delete(int id)throws DAOException;
    public abstract boolean delete(T entity)throws DAOException;
    public abstract boolean create(T entity)throws DAOException;
    public abstract boolean update(T entity)throws DAOException;
}
