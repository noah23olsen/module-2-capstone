package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    void create(Transfer transfer);

    void updateFromAccount(Transfer transfer);

    void updateToAccount(Transfer transfer);

    List<Transfer> listAllTransfers(int userId);
}
