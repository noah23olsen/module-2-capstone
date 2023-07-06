package com.techelevator.tenmo.model;

public class Account {
    private double teBucks;
    private int userId;

    public Account(double teBucks){
        this.teBucks=teBucks;
    }

    public Account(int userId, double teBucks){
        this.userId = userId;
        this.teBucks = teBucks;
    }

    public double getTeBucks() {
        return teBucks;
    }

    public void setTeBucks(double teBucks) {
        this.teBucks = teBucks;
    }
}
