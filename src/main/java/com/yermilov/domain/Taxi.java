package com.yermilov.domain;

public class Taxi {
    private int taxiId,driverId,taxiTypeId;
    private String taxiModel;

    public Taxi(int driverId, int taxiTypeId, String taxiModel) {
        this.driverId = driverId;
        this.taxiTypeId = taxiTypeId;
        this.taxiModel = taxiModel;
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

    public String getTaxiModel() {
        return taxiModel;
    }

    public void setTaxiModel(String taxiModel) {
        this.taxiModel = taxiModel;
    }
}
