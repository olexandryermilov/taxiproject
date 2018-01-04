package com.yermilov.domain;

public class ClientType {
    private int clientTypeId,discount;
    private String name;
    private double moneySpent;

    public ClientType(int discount, String name, double moneySpent) {
        this.discount = discount;
        this.name = name;
        this.moneySpent = moneySpent;
    }

    public int getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(int clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }
}
