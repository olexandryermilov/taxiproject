package com.yermilov.dao;

import com.yermilov.domain.Taxi;

import java.util.List;

public class TaxiDAO extends AbstractDAO<Taxi> {
    public Taxi findByCarNumber(String carNumber){
        return null;
    }
    @Override
    public List<Taxi> findAll() {
        return null;
    }

    @Override
    public Taxi findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Taxi entity) {
        return false;
    }

    @Override
    public boolean create(Taxi entity) {
        return false;
    }

    @Override
    public Taxi update(Taxi entity) {
        return null;
    }
}
