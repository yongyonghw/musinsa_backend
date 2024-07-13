DROP TABLE if EXISTS ITEM;
DROP TABLE if EXISTS BRAND;
DROP TABLE if EXISTS CATEGORY;

CREATE TABLE ITEM (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         price INT,
                         brand_id INT,
                         category_id INT
);

CREATE TABLE BRAND (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE CATEGORY (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

