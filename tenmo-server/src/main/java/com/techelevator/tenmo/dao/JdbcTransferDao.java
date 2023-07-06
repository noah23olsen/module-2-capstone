package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public void create(Transfer transfer) {

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (2, 2, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?);";



        jdbcTemplate.update(sql, transfer.getUserIdFrom(),transfer.getUserIdTo(),transfer.getAmount());



    }

    @Override
    public void updateFromAccount(Transfer transfer) {
        String sql = "UPDATE account  SET balance = (balance - ?) WHERE user_id = ?";

        jdbcTemplate.update(sql,transfer.getAmount(),transfer.getUserIdFrom());
    }

    public void updateToAccount(Transfer transfer){
        String sql = "UPDATE account  SET balance = (balance + ?) WHERE user_id = ?";

        jdbcTemplate.update(sql,transfer.getAmount(), transfer.getUserIdFrom());
    }
    //update to account (make method as well in transfer dao
    //put both in acconunt controller
    //put in client
    //test sql statements in pgadmin 
}
