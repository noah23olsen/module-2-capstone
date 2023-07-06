select * from transfer
select * from account

INSERT INTO transfer (account_from, account_to, amount) VALUES (2, 2, ?, ?, ?);

UPDATE account  SET balance = (balance + ?) WHERE user_id = ?
