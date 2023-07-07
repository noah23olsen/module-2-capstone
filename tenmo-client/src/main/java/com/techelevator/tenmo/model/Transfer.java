package com.techelevator.tenmo.model;

import java.util.Objects;

public class Transfer {
    private double amount;

    private int transfer_type;
    private int transfer_status;

    private int userIdTo;
    private int userIdFrom;
    private int transfer_id;
    public Transfer(){

    }

    public Transfer(double amount, int userIdTo, int userIdFrom) {
        this.amount = amount;
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
    }
    public Transfer(int transfer_type, int transfer_status, int userIdFrom, int userIdTo,double amount) {
        this.amount = amount;
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
        this.transfer_type= transfer_type;
        this.transfer_status = transfer_status;
    }
    public Transfer(int transfer_id, int transfer_type, int transfer_status, int userIdFrom, int userIdTo,double amount) {
        this.amount = amount;
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
        this.transfer_type= transfer_type;
        this.transfer_status = transfer_status;
        this.transfer_id = transfer_id;
    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(int transfer_id) {
        this.transfer_id = transfer_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Double.compare(transfer.amount, amount) == 0 && transfer_type == transfer.transfer_type && transfer_status == transfer.transfer_status && userIdTo == transfer.userIdTo && userIdFrom == transfer.userIdFrom && transfer_id == transfer.transfer_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, transfer_type, transfer_status, userIdTo, userIdFrom, transfer_id);
    }
}

