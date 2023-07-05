package com.techelevator.dao;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcAccountDaoTests extends BaseDaoTests{

    private AccountDao sut;

    @Before
    public void setup(){
        sut = new JdbcAccountDao(dataSource);
    }

    @Test
    public void get_TE_bucks_for_user(){
        Account account = sut.getAccountForUser(1001);
        Assert.assertEquals(1000,account.getTeBucks(),.009);
    }
}
