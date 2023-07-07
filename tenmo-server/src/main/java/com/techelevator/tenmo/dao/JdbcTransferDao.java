package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

        jdbcTemplate.update(sql,transfer.getAmount(), transfer.getUserIdTo());
    }

    @Override
    public List<Transfer> listAllTransfers(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id,transfer_type_id, transfer_status_id, \n" +
                "a1.user_id AS from_user, a2.user_id AS to_user, amount \n" +
                "from transfer\n" +
                "JOIN account AS a1 on transfer.account_from = a1.account_id\n" +
                "JOIN account AS a2 on transfer.account_to = a2.account_id\n" +
                "JOIN tenmo_user ON a1.user_id = tenmo_user.user_id\n" +
                "WHERE a1.user_id = ? OR a2.user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId,userId);
        while (results.next())   {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }
    //TODO: fix our SQL joins
    //TODO: fix list all transfers method, pass principal where its a ?
    //TODO: move our stuff to client side better
    //TODO: call our things in the app.java

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransfer_status(rs.getInt("transfer_status_id"));
        transfer.setTransfer_type(rs.getInt("transfer_type_id"));
        transfer.setAmount(rs.getDouble("amount"));
        transfer.setUserIdFrom(rs.getInt("account_from"));
        transfer.setUserIdTo(rs.getInt("account_to"));
        return transfer;
    }
}
