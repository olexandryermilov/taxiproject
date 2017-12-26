package com.taxisystem.dao;

import com.taxisystem.domain.Ride;

import java.util.List;

public class RideDAO extends AbstractDAO<Ride> {
    @Override
    public List<Ride> findAll() {
        return null;
    }

    @Override
    public Ride findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Ride entity) {
        return false;
    }

    @Override
    public boolean create(Ride entity) {
        return false;
    }

    @Override
    public Ride update(Ride entity) {
        return null;
    }
}
