package com.yermilov.domain;

import java.sql.Date;

public class Ride {
    private int rideId,driverId,clientId,taxiId;
    private double cost, distance;
    private Date rideStart, rideFinish;

    public Ride(int driverId, int clientId, int taxiId, double cost, double distance, Date rideStart, Date rideFinish) {
        this.driverId = driverId;
        this.clientId = clientId;
        this.taxiId = taxiId;
        this.cost = cost;
        this.distance = distance;
        this.rideStart = rideStart;
        this.rideFinish = rideFinish;
    }

    public int getRideId() {
        return rideId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getRideStart() {
        return rideStart;
    }

    public void setRideStart(Date rideStart) {
        this.rideStart = rideStart;
    }

    public Date getRideFinish() {
        return rideFinish;
    }

    public void setRideFinish(Date rideFinish) {
        this.rideFinish = rideFinish;
    }
}
