package com.yermilov.domain;

public class TaxiType {
    private int taxiTypeId;
    private String taxiTypeName;

    public TaxiType(String taxiTypeName) {
        this.taxiTypeName = taxiTypeName;
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
}
