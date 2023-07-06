package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransferDaoTests extends BaseDaoTests {
    private TransferDao sut;

    private static final Account ACCOUNT_1 = new Account(1001,1000);
    private static final Account ACCOUNT_3 = new Account(1003,0);

    @Before
    public void setup() {
        sut = new JdbcTransferDao(dataSource);
    }

    @Test
    public void transfer_between_two_accounts() {
        Transfer transfer = new Transfer(100, 1003, 1001);

        sut.updateFromAccount(transfer);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        double teBucks = 0.00;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,1001);

        if (rows.next()) {
            teBucks = rows.getDouble("balance");
        }


        Assert.assertEquals(900.00,teBucks,.009);
//        (1001, 1000)
//        (1003, 0)
    }

}
