CREATE DATABASE furniture_shop;

USE furniture_shop;

CREATE TABLE User
(
user_id int NOT NULL,
username varchar(50) NOT NULL,
password varchar(50) NOT NULL,
email varchar(50),
first_name varchar(50),
last_name varchar(50),
phone_number int,
address varchar(200),
PRIMARY KEY(user_id)
);


CREATE TABLE Orders
(
order_id int NOT NULL,
date_time DateTime NOT NULL,
price float NOT NULL,
status varchar(50),
user_id int NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY(user_id) REFERENCES User(user_id)
);

CREATE TABLE Main_category
(
main_category_id int NOT NULL,
name varchar(200) NOT NULL,
PRIMARY KEY(main_category_id)
);

CREATE TABLE Sub_category
(
sub_category_id int NOT NULL,
name varchar(200) NOT NULL,
main_category_id int NOT NULL,
PRIMARY KEY(sub_category_id),
FOREIGN KEY(main_category_id) REFERENCES Main_category(main_category_id)
);

CREATE TABLE Product
(
product_id int NOT NULL,
name varchar(100) NOT NULL,
price float NOT NULL,
description varchar(1000) NOT NULL,
image varchar(1000) NOT NULL,
sub_category_id int NOT NULL,
PRIMARY KEY(product_id),
FOREIGN KEY(sub_category_id) REFERENCES Sub_category(sub_category_id)
);

CREATE TABLE Orders_Product
(
order_id int NOT NULL,
product_id int NOT NULL,
quantity int NOT NULL,
PRIMARY KEY(order_id, product_id),
FOREIGN KEY(order_id) REFERENCES Orders(order_id),
FOREIGN KEY(product_id) REFERENCES Product(product_id)
);

CREATE TABLE Label
(
label_id int NOT NULL,
name varchar(50) NOT NULL,
PRIMARY KEY(label_id)
);

CREATE TABLE Product_Label
(
product_id int NOT NULL,
label_id int NOT NULL,
label_value varchar(200) NOT NULL,
PRIMARY KEY(product_id, label_id),
FOREIGN KEY(product_id) REFERENCES Product(product_id),
FOREIGN KEY(label_id) REFERENCES Label(label_id)
);



