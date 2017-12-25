package com.taxisystem.domain;

public class TaxiType {
    private int taxiTypeId;
    private String taxiTypeName;

    public TaxiType(int taxiTypeId, String taxiTypeName) {
        this.taxiTypeId = taxiTypeId;
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
