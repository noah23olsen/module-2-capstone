package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {
    void create(Transfer transfer);

    void updateFromAccount(Transfer transfer);


}
