package com.yermilov.domain;

public class Taxi {
    private int taxiId,driverId,taxiTypeId;
    private String carNumber;

    public Taxi(int driverId, int taxiTypeId, String carNumber) {
        this.driverId = driverId;
        this.taxiTypeId = taxiTypeId;
        this.carNumber = carNumber;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getTaxiTypeId() {
        return taxiTypeId;
    }

    public void setTaxiTypeId(int taxiTypeId) {
        this.taxiTypeId = taxiTypeId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
