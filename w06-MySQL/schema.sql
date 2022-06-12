create database Mall;

use Mall;

CREATE TABLE User
(
id int,
username varchar(255),
password varchar(255),
create_time date,
update_time date
);

CREATE TABLE Product
(
id int,
productName varchar(255),
create_time date,
update_time date
);

CREATE TABLE `Order`
(
id int,
userId int,
productId int,
create_time date,
update_time date
);