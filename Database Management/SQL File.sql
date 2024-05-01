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
role boolean DEFAULT 0,
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
(1,'admin','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','admin@gmail.com','Admin','Account','0765708890','Colombo, 12',1),
(2,'nonadmin','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','nonadmin@gmail.com','NonAdmin','Account','0765708890','Pitipana road, Homagama',0);


INSERT INTO Main_category VALUES 
(1,'Living Room'),
(2,'Dining Room'),
(3,'Bedroom');

INSERT INTO Sub_category VALUES 
(1,'Sofas & Couches',1),
(2,'Coffee Tables',1),
(3,'Dining Table Sets',2),
(4,'Dining Chairs',2),
(5,'Beds',3),
(6,'Bedroom Cupboards',3);

INSERT INTO Product VALUES 
(1,'ZEDGE LEATHER L-SOFA',171200,171200,'This high-quality L-shaped leather sofa is effortlessly smart. perfect for creating a luxurious living area. Its soft leather and button details make it both comfortable and on-trend. The leather version made with high-quality materials has more cuts and stitching than the fabric version. allowing for a different look depending on your choice of material.','sofa_img.jpg',1),
(2,'DOUBLE DIVAN SOFA',224400,224400,'One sofa. lots of possibilities. Need extra beds or a comfy reading corner? No problem. Just choose the pieces you like combine them as you want and change when you feel like it. Its a great way to add comfy seating to a smaller space or complement a larger sofa in the room. The inside frame of the arm and backrest is cut out and covered with a soft cushion. This makes both the armrest and the backrest very soft and optimizes your comfort.','aa',1),
(3,'Belvedere Coffee Table',69000,69000,'The contemporary work of the Belevedere coffee table is sturdy and stylish. Its fine structure and timeless design style add class to your living room.','bed.jpg',2),
(4,'Dexter Coffee Table',48500,48500,'Unifying durability with functionality this hand-crafted coffee table resonates with an antique appeal lending an old-world charm to your living spaces.','chair.png',2),
(5,'Pearl Dining Suite',75000,75000,'Pearl 5 piece dining suite crafted with treated wood for a solid output. Smooth finish of the beveled edge tabletop with sturdy legs brings out a satisfactory appeal. Sufficiently spaced fabric chairs with attractive wooden backrest patterns allows comfortable dining.','22.png',3),
(6,'Nevada Dining Suite',188000,188000,'Create unique experiences in your family get-togethers with the precisely finished vast spaciousness of the Nevada 9 piece dining suite. Thick tabletop with bobbin patterned and tapered legs brings a satisfactory feel where the large fabric chairs with curvy backrests exhibits classiness.','1.png',3),
(7,'Wellington Wooden Dining Chair',18000,18000,'Unique dining chair made out of treated wood that composites a classical touch. Smoothly connected back rest panels with a slight curve provides a comforting posture where the padded seat leads to softness.','dd',4),
(8,'Nevada Wooden Dining Chair',15000,15000,'An exceptional chair design with stretched vertical slat back and bulky structure to bring a supreme feel. Sturdiness of treated wood with soft fabric upholstered seat allows comfortable seating.','ff',4),
(9,'Ardly Bed',65000,65000,'Exceptionally simple and sophisticated. the Ardly bed along with the bedside cupboards can add a minimalistic sense of elegance to your bedroom.','gg',5),
(10,'Athena Bed',110500,110500,'Contemporary design coupled with excellent craftsmanship. The Athena units compliment your bedroom with a touch of elegance & novelty.','kk',5),
(11,'Regent 2 Door Wardrobe (KWRE 002)',50000,50000,'Tall and slender design with lockable swing doors. Comes with a hanger bar and a drawer and generously spaced shelves for a much organised arrangement. Scratch resistant melamine faced structure will ensure durability whereas large handles will encourage effortless operation.','gg',6),
(12,'Beverly 2 Door Wardrobe KWB 002',74000,74000,'A product with a clean finish that brings neatness to your bedroom. Fine swing doors with chrome detailed handles and spacious shelves. Dedicated hanging compartment will preserve the freshness of your clothes whereas the drawer will accommodate your valuables.','gg',6);

INSERT INTO Product_stock VALUES
(1,'Nimal Senadeera','2024-04-08 15:30:00',150000,6,5,1),
(2,'Kamal Siripala','2024-04-09 15:30:00',200000,5,5,2),
(3,'Sahan Nilgama','2024-04-11 15:30:00',50000,5,5,3),
(4,'Sahan Nilgama','2024-04-12 15:30:00',40000,6,5,4),
(5,'Kamal Siripala','2024-04-12 15:30:00',60000,5,5,5),
(6,'Kamal Siripala','2024-04-13 15:30:00',150000,5,5,6),
(7,'Nimal Senadeera','2024-04-14 15:30:00',10000,5,5,7),
(8,'Nimal Senadeera','2024-04-15 15:30:00',10000,5,5,8),
(9,'Sahan Nilgama','2024-04-16 15:30:00',40000,6,5,9),
(10,'Sahan Nilgama','2024-04-17 15:30:00',90000,5,5,10),
(11,'Kamal Siripala','2024-04-18 15:30:00',40000,6,5,11),
(12,'Kamal Siripala','2024-04-20 15:30:00',50000,5,5,12);

INSERT INTO Orders VALUES
(1,'2024-04-20 15:30:00',171200,'Pending', 'Colombo 12', 'Nimal Gunerathne', '0777306578', 2),
(2,'2024-04-25 15:30:00',115000,'Dispatched','Colombo 9', 'Boyd Dias', '0777107890', 2),
(3,'2024-04-25 15:30:00',48500,'Delivered','Horana 10','Kamal Gunasignha', '0765798899',2);

INSERT INTO Orders_Product VALUES
(1,171200,1,1,1),
(2,65000,1,2,9),
(3,50000,1,2,11),
(4,48500,1,3,4);



