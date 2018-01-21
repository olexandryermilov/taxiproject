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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ride ride = (Ride) o;

        if (rideId != ride.rideId) return false;
        if (driverId != ride.driverId) return false;
        if (clientId != ride.clientId) return false;
        if (taxiId != ride.taxiId) return false;
        if (Double.compare(ride.cost, cost) != 0) return false;
        if (Double.compare(ride.distance, distance) != 0) return false;
        if (rideStart != null ? !rideStart.equals(ride.rideStart) : ride.rideStart != null) return false;
        return rideFinish != null ? rideFinish.equals(ride.rideFinish) : ride.rideFinish == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = rideId;
        result = 31 * result + driverId;
        result = 31 * result + clientId;
        result = 31 * result + taxiId;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (rideStart != null ? rideStart.hashCode() : 0);
        result = 31 * result + (rideFinish != null ? rideFinish.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", driverId=" + driverId +
                ", clientId=" + clientId +
                ", taxiId=" + taxiId +
                ", cost=" + cost +
                ", distance=" + distance +
                ", rideStart=" + rideStart +
                ", rideFinish=" + rideFinish +
                '}';
    }
}
