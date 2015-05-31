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

--create product table
CREATE TABLE product(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  item_code CHAR(10) NOT NULL UNIQUE,
  item_name VARCHAR(50),
  price DECIMAL(6, 2),
  detail VARCHAR(200),
  create_date TIMESTAMP NOT NULL,
  update_date TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create product_pic table
CREATE TABLE product_pic(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  pic BLOB NOT NULL,
  product_id BIGINT NOT NULL UNIQUE,
  create_date TIMESTAMP NOT NULL,
  update_date TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create stock
CREATE TABLE stock(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  stock INT NOT NULL,
  product_id BIGINT NOT NULL UNIQUE,
  create_date TIMESTAMP NOT NULL,
  update_date TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);
