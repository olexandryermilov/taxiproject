package com.yermilov.domain;

public class Client {
    private int clientId,userId;

    public Client(int userId) {
        this.userId = userId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
