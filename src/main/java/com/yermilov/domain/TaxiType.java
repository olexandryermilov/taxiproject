package com.yermilov.domain;

public class TaxiType {
    private int taxiTypeId;
    private double fare;
    private String taxiTypeName;

    public TaxiType(double fare, String taxiTypeName) {
        this.taxiTypeName = taxiTypeName;
        this.fare=fare;
    }

    public int getTaxiTypeId() {
        return taxiTypeId;
    }

    public void setTaxiTypeId(int taxiTypeId) {
        this.taxiTypeId = taxiTypeId;
    }

    public String getTaxiTypeName() {
        return taxiTypeName;
    }

    public void setTaxiTypeName(String taxiTypeName) {
        this.taxiTypeName = taxiTypeName;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
