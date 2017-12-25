package com.taxisystem.domain;

public class Client {
    private int clientId,userId;

    public Client(int clientId, int userId) {
        this.clientId = clientId;
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
