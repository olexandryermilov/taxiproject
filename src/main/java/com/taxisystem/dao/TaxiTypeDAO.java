package com.taxisystem.dao;

import com.taxisystem.domain.TaxiType;

import java.util.List;

public class TaxiTypeDAO extends AbstractDAO<TaxiType> {
    @Override
    public List<TaxiType> findAll() {
        return null;
    }

    @Override
    public TaxiType findById(int id) {
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

    @Override
    public boolean create(TaxiType entity) {
        return false;
    }

    @Override
    public TaxiType update(TaxiType entity) {
        return null;
    }
}
