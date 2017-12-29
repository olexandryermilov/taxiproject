package com.yermilov.dao;

import java.sql.Driver;
import java.util.List;

public class DriverDAO extends AbstractDAO<Driver> {

    public DriverDAO(){

    }
    @Override
    public List<Driver> findAll() {
        return null;
    }

    @Override
    public Driver findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Driver entity) {
        return false;
    }

    @Override
    public boolean create(Driver entity) {
        return false;
    }

    @Override
    public Driver update(Driver entity) {
        return null;
    }
}
