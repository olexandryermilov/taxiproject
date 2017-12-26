package com.taxisystem.dao;

import com.taxisystem.domain.ClientType;

import java.util.List;

public class ClientTypeDAO extends AbstractDAO<ClientType> {
    private static final String SQL_SELECT_ALL = "select * from taxisystemdb.clienttype";
    private final static String SQL_SELECT_BY_ID = "select * from taxisystemdb.clienttype where clienttypeid=?";
    private final static String SQL_DELETE_BY_ID = "delete from taxisystemdb.clienttype where clienttypeid=?";
    private static final String SQL_INSERT_CLIENT="insert into taxisystemdb.clienttype(userid) values(?)";
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
