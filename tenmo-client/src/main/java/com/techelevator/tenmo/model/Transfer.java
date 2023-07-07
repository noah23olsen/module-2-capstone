package com.techelevator.tenmo.model;

public class Transfer {
    private double amount;

    private int transfer_type;
    private int transfer_status;

    private int userIdTo;
    private int userIdFrom;
    public Transfer(double amount, int userIdTo, int userIdFrom) {
        this.amount = amount;
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
    }

    public Transfer(double amount, int userIdTo, int userIdFrom, int transfer_status, int transfer_type) {
        this.amount = amount;
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
        this.transfer_status = transfer_status;
        this.transfer_type = transfer_type;
    }

    public int getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(int transfer_type) {
        this.transfer_type = transfer_type;
    }

    public int getTransfer_status() {
        return transfer_status;
    }

    public void setTransfer_status(int transfer_status) {
        this.transfer_status = transfer_status;
    }
}
