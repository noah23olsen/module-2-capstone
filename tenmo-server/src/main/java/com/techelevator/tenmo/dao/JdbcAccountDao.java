package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Account getAccountForUser(int userId) {
        double teBucks = 0.00;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,userId);

        if (rows.next()) {
            teBucks = rows.getDouble("balance");
        }
        return new Account(teBucks);

    }


}
