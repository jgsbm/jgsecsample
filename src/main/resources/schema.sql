--create customers table
CREATE TABLE customers(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  customer_name VARCHAR(30) NOT NULL,
  address VARCHAR(255) NOT NULL,
  tel VARCHAR(11) NOT NULL,
  hashed_password VARCHAR(128) NOT NULL,
  email VARCHAR(256) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create creditcards tables
CREATE TABLE creditcards(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  encrypted_creditno VARCHAR(96) NOT NULL,
  customer_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create products table
CREATE TABLE products(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  item_code CHAR(10) NOT NULL UNIQUE,
  item_name VARCHAR(50),
  price DECIMAL(6, 2),
  detail VARCHAR(200),
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create product_images table
CREATE TABLE product_images(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  pic BLOB NOT NULL,
  product_id BIGINT NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);

--create stocks table
CREATE TABLE stocks(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  stock INT NOT NULL,
  product_id BIGINT NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  version BIGINT NOT NULL
);
