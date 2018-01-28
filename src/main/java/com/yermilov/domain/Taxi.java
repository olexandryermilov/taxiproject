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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taxi taxi = (Taxi) o;

        if (taxiId != taxi.taxiId) return false;
        if (driverId != taxi.driverId) return false;
        if (taxiTypeId != taxi.taxiTypeId) return false;
        return carNumber != null ? carNumber.equals(taxi.carNumber) : taxi.carNumber == null;
    }

    @Override
    public int hashCode() {
        int result = taxiId;
        result = 31 * result + driverId;
        result = 31 * result + taxiTypeId;
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "taxiId=" + taxiId +
                ", driverId=" + driverId +
                ", taxiTypeId=" + taxiTypeId +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}
