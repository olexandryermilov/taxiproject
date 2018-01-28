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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientType that = (ClientType) o;

        if (clientTypeId != that.clientTypeId) return false;
        if (discount != that.discount) return false;
        if (Double.compare(that.moneySpent, moneySpent) != 0) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = clientTypeId;
        result = 31 * result + discount;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(moneySpent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ClientType{" +
                "clientTypeId=" + clientTypeId +
                ", discount=" + discount +
                ", name='" + name + '\'' +
                ", moneySpent=" + moneySpent +
                '}';
    }
}
