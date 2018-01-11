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
}
