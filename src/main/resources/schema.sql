DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;




CREATE TABLE accounts(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_name varchar(255),
    balance DOUBLE NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE transactions(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_account_id BIGINT,
    to_account_id BIGINT,
    amount DOUBLE NOT NULL,
    FOREIGN KEY (from_account_id) REFERENCES accounts(id),
    FOREIGN KEY (to_account_id) REFERENCES accounts(id)
);