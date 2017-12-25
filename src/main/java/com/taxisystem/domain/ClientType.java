package com.taxisystem.domain;

public class ClientType {
    private int clientTypeId;
    private String clientTypeName;

    public ClientType(int clientTypeId, String clientTypeName) {
        this.clientTypeId = clientTypeId;
        this.clientTypeName = clientTypeName;
    }

    public int getClientTypeId() {
        return clientTypeId;
    }

    public String getClientTypeName() {
        return clientTypeName;
    }

    public void setClientTypeName(String clientTypeName) {
        this.clientTypeName = clientTypeName;
    }
}
