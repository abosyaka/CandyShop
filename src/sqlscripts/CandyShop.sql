-- CREATE DATABASE "CandyShop"
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'Russian_Russia.1251'
--     LC_CTYPE = 'Russian_Russia.1251'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;
--
-- CREATE SCHEMA public
--     AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS Role(
  role_id   SERIAL PRIMARY KEY,
  role_name VARCHAR(55) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_detail(
  user_id       SERIAL PRIMARY KEY,
  user_email    VARCHAR(127) UNIQUE NOT NULL,
  user_password VARCHAR(255) NOT NULL,
  user_name     VARCHAR(255) NOT NULL,
  role_id       INT,
  FOREIGN KEY (role_id) REFERENCES Role(role_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Category(
  category_id   SERIAL PRIMARY KEY,
  category_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Good(
  good_id           SERIAL PRIMARY KEY,
  good_name         VARCHAR(255) NOT NULL,
  good_description  TEXT,
  picture_url       VARCHAR(255),
  good_weight       FLOAT NOT NULL,
  price             INT NOT NULL,
  category_id       INT NOT NULL,
  ingredients       TEXT NOT NULL,
  storage_period    INT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES Category(category_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Status(
  status_id   SERIAL NOT NULL PRIMARY KEY,
  status_name VARCHAR(55) NOT NULL
);

CREATE TABLE IF NOT EXISTS purchase_order
(
  order_id    SERIAL PRIMARY KEY,
  user_id     INT NOT NULL,
  status_id   INT NOT NULL,
  order_date  TIMESTAMP DEFAULT NOW(),
  FOREIGN KEY (user_id) REFERENCES user_detail (user_id) ON DELETE CASCADE,
  FOREIGN KEY(status_id) REFERENCES Status(status_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Order_Detail(
  detail_id SERIAL PRIMARY KEY,
  order_id INT NOT NULL,
  good_id INT NOT NULL,
  count INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES purchase_order(order_id) ON DELETE CASCADE,
  FOREIGN KEY (good_id) REFERENCES Good(good_id) ON DELETE CASCADE
);