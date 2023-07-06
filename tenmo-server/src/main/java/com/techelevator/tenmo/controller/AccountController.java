package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
//TODO: fix this
@PreAuthorize("isAuthenticated()")
@RequestMapping (path = "/accounts")
public class AccountController {
    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Account getAccountForUser(Principal principal) {
        String username = principal.getName();

        int userId = userDao.findIdByUsername(username);
        Account account = accountDao.getAccountForUser(userId);


        return account;
    }
    @RequestMapping(path = "/transfers", method = RequestMethod.POST)
    public void transferBetweenAccounts(Principal principal, @RequestBody Transfer transfer) {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        if (userId == transfer.getUserIdFrom()) {
            //update from balance
            //update to balance
            transferDao.create(transfer);

        }

    }






}
