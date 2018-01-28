package com.yermilov.domain;

public class Driver {
    private int driverId, userId;

    public Driver(int userId) {
        this.userId = userId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDriverId() {
        return driverId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (driverId != driver.driverId) return false;
        return userId == driver.userId;
    }

    @Override
    public int hashCode() {
        int result = driverId;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", userId=" + userId +
                '}';
    }
}
