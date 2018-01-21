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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiType taxiType = (TaxiType) o;

        if (taxiTypeId != taxiType.taxiTypeId) return false;
        if (Double.compare(taxiType.fare, fare) != 0) return false;
        return taxiTypeName != null ? taxiTypeName.equals(taxiType.taxiTypeName) : taxiType.taxiTypeName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = taxiTypeId;
        temp = Double.doubleToLongBits(fare);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (taxiTypeName != null ? taxiTypeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaxiType{" +
                "taxiTypeId=" + taxiTypeId +
                ", fare=" + fare +
                ", taxiTypeName='" + taxiTypeName + '\'' +
                '}';
    }
}
