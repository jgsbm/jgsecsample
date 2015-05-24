--create customer table
CREATE TABLE customer(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  customer_name VARCHAR(30) NOT NULL,
  address VARCHAR(255) NOT NULL,
  tel VARCHAR(11) NOT NULL,
  hashed_password VARCHAR(128) NOT NULL,
  email VARCHAR(256) NOT NULL UNIQUE,
  create_date TIMESTAMP NOT NULL,
  update_date TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);