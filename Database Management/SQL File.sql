CREATE DATABASE furniture_shop;

USE furniture_shop;


CREATE TABLE User
(
user_id int NOT NULL AUTO_INCREMENT,
username varchar(50) NOT NULL,
password varchar(1000) NOT NULL,
email varchar(50),
first_name varchar(50),
last_name varchar(50),
phone_number varchar(20),
address varchar(200),
PRIMARY KEY(user_id)
);


CREATE TABLE Main_category
(
main_category_id int NOT NULL AUTO_INCREMENT,
name varchar(200) NOT NULL,
PRIMARY KEY(main_category_id)
);


CREATE TABLE Sub_category
(
sub_category_id int NOT NULL AUTO_INCREMENT,
name varchar(200) NOT NULL,
main_category_id int NOT NULL,
PRIMARY KEY(sub_category_id),
FOREIGN KEY(main_category_id) REFERENCES Main_category(main_category_id)
);


CREATE TABLE Label
(
label_id int NOT NULL AUTO_INCREMENT,
name varchar(50) NOT NULL,
PRIMARY KEY(label_id)
);


CREATE TABLE Product
(
product_id int NOT NULL AUTO_INCREMENT,
name varchar(100) NOT NULL,
buying_price float NOT NULL,
selling_price float NOT NULL,
description varchar(1000) NOT NULL,
image varchar(1000) NOT NULL,
sub_category_id int NOT NULL,
PRIMARY KEY(product_id),
FOREIGN KEY(sub_category_id) REFERENCES Sub_category(sub_category_id)
);


CREATE TABLE Product_Label
(
product_id int NOT NULL AUTO_INCREMENT,
label_id int NOT NULL,
label_value varchar(200) NOT NULL,
PRIMARY KEY(product_id, label_id),
FOREIGN KEY(product_id) REFERENCES Product(product_id),
FOREIGN KEY(label_id) REFERENCES Label(label_id)
);


CREATE TABLE Product_stock
(
stock_id int NOT NULL AUTO_INCREMENT,
supplier_name varchar(200) NOT NULL,
date_time DateTime NOT NULL,
buying_price float NOT NULL,
supplied_quantity int NOT NULL,
available_quantity int NOT NULL,
product_id int NOT NULL,
PRIMARY KEY(stock_id),
FOREIGN KEY(product_id) REFERENCES Product(product_id)
);


CREATE TABLE Orders
(
order_id int NOT NULL AUTO_INCREMENT,
date_time DateTime NOT NULL,
total_price float NOT NULL,
status varchar(50),
shipping_address varchar(1000) NOT NULL,
receiver_name varchar(100) NOT NULL,
receiver_phone_number varchar(20) NOT NULL,
user_id int NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY(user_id) REFERENCES User(user_id)
);


CREATE TABLE Orders_Product
(
orders_product_id int NOT NULL AUTO_INCREMENT,
selling_price float NOT NULL,
quantity int NOT NULL,
order_id int NOT NULL,
stock_id int NOT NULL,
PRIMARY KEY(orders_product_id),
FOREIGN KEY(order_id) REFERENCES Orders(order_id),
FOREIGN KEY(stock_id) REFERENCES Product_stock(stock_id)
);


CREATE TABLE Product_review
(
review_id int NOT NULL AUTO_INCREMENT,
comment varchar(16000) NOT NULL,
order_id int NOT NULL,
product_id int NOT NULL,
PRIMARY KEY(review_id),
FOREIGN KEY(order_id) REFERENCES Orders(order_id),
FOREIGN KEY(product_id) REFERENCES Product(product_id)
);


CREATE TABLE Review_image
(
image_id int NOT NULL AUTO_INCREMENT,
image varchar(16000) NOT NULL,
review_id int NOT NULL,
PRIMARY KEY(image_id),
FOREIGN KEY(review_id) REFERENCES Product_review(review_id)
);



INSERT INTO User VALUES 
(1,'kamal123','a5757ab46ba6bce31ebfef29894a435ce1fdc7c8bf03f6b83f3c85bc7931fa74','kamal@gmail.com','Kamal','Gunerathne','0765800001','Colombo, 12'),
(2,'nimal456','a5757ab46ba6bce31ebfef29894a435ce1fdc7c8bf03f6b83f3c85bc7931fa74','nimal@gmail.com','Nimal','Gamage','0765800002','Pitipana road, Homagama'),
(3,'johndoe','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','jogn@gmail.com','John','Doe','0765800003','Godagama, Homagama'),
(4,'kathrina6789','f14f286ca435d1fa3b9d8041e8f06aa0af7ab28ea8edcd7e11fd485a100b632b','kathi@gmail.com','Kathrina','Pierce','0765800004','Poruwadanda, Horana');

INSERT INTO Main_category VALUES 
(1,'Living Room'),
(2,'Dining Room'),
(3,'Bed Room');

INSERT INTO Sub_category VALUES 
(1,'Sofas',1),
(2,'Dining Tables',2),
(3,'Dining Chairs',2),
(4,'Beds',3),
(5,'Living Room Chairs',1),
(6,'Bed Room Cupboards',3),
(7,'Living Room Tables',1);

INSERT INTO Label VALUES 
(1,'Color'),
(2,'Material');

INSERT INTO Product VALUES 
(1,'Leather Sofa',33000,43000,'Premium leather sofa','sofa_img.jpg',1),
(2,'Wooden Dining Table',13000,23000,'Solid wood dining table','table_img.jpg',2),
(3,'Queen Bed',50000,64000,'Sturdy queen-sized bed','bed.jpg',4),
(4,'Dining Chair Set',8000,12000,'Set of 4 upholstered dining chairs','chair.png',3);

INSERT INTO Product_Label VALUES 
(1,1,'Brown'),
(1,2,'Leather'),
(2,1,'Brown'),
(2,2,'Wood'),
(3,1,'Dark Red'),
(3,2,'Mahogani Wood'),
(4,1,'Green'),
(4,2,'Wood');

INSERT INTO Product_stock VALUES
(1,'Nimal Senadeera','2024-04-08 15:30:00',40000,20,18,1),
(2,'Kamal Siripala','2024-04-12 15:30:00',20000,10,10,2),
(3,'Sahan Nilgama','2025-04-08 15:30:00',50000,5,5,1),
(4,'Sahan Nilgama','2025-04-08 15:30:00',50000,10,10,4);


INSERT INTO Orders VALUES 
(1,'2024-04-08 15:30:00',66000,'Pending','Pitipana road, Homagama','Kamal Gunerathne','0765800001',1),
(2,'2024-04-12 07:30:00',35000,'Delivered','Godagama road, Homagama','John Doe','0765800003',3),
(3,'2024-05-12 09:30:00',55000,'Pending','Pitipana road, Homagama','Kathrina Pierece','0765800004',4),
(4,'2024-06-12 09:30:00',36000,'Delivered','Godagama road, Homagama','Kamal Gunerathne','0765800001',1);

INSERT INTO Orders_Product VALUES 
(1,43000,1,1,1),
(2,23000,1,1,2),
(3,23000,1,2,2),
(4,12000,1,2,4),
(5,43000,1,3,1),
(6,12000,1,3,4),
(7,12000,3,4,4);


INSERT INTO Product_review VALUES
(1,'This product is highly appreciated',1,1),
(2,'This product is quite bad, not good',1,2),
(3,'This product is highly appreciated',2,4);

INSERT INTO Review_image VALUES
(1,'image1.png',1),
(2,'image2.jpeg',1),
(3,'image3.png',3);



