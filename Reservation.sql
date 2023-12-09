create database Reservation;
use Reservation;
create table user(id int primary key auto_increment,uname varchar(30),upwd varchar(15),uemail varchar(25),umobile varchar(13));
INSERT INTO user (uname, upwd, uemail, umobile) VALUES ('Student', '1234', 'student@gmail.com', '123-456-7890');
create table admin(uname varchar(30),upwd varchar(15));
insert into admin(uname,upwd)  values ('admin@gmail.com','admin');
CREATE TABLE reserve(pnr INT AUTO_INCREMENT PRIMARY KEY,origin VARCHAR(50) NOT NULL,dest VARCHAR(50) NOT NULL,jclass VARCHAR(10) NOT NULL,jdate DATE NOT NULL,no_of_pass INT NOT NULL);
INSERT INTO reserve (pnr, origin, dest, jclass, jdate, no_of_pass) VALUES (659833, 'Delhi', 'Kolkata', '1A', '2023-12-15', 2);
INSERT INTO reserve (origin, dest, jclass, jdate, no_of_pass) VALUES ('Chennai', 'Mumbai', '2A', '2023-12-20', 4);
CREATE TABLE train (trn_no INT AUTO_INCREMENT PRIMARY KEY,tName VARCHAR(255) NOT NULL,origin VARCHAR(255) NOT NULL,dest VARCHAR(255) NOT NULL,aTime TIME NOT NULL,dTime TIME NOT NULL,1A INT NOT NULL,2A INT NOT NULL,3A INT NOT NULL,3E INT NOT NULL,SL INT NOT NULL);
INSERT INTO train (trn_no, tName, origin, dest, aTime, dTime, 1A, 2A, 3A, 3E, SL) 
VALUES (124578, 'NDLS-HWH Express', 'New Delhi', 'Howrah', '08:00:00', '14:00:00', 100, 200, 300, 50, 500);
INSERT INTO train (trn_no, tName, origin, dest, aTime, dTime, 1A, 2A, 3A, 3E, SL)
VALUES
(12345, 'Shatabdi Express', 'Delhi', 'Mumbai', '08:00', '14:00', 20, 50, 100, 150, 300),
(23456, 'Rajdhani Express', 'Kolkata', 'Delhi', '19:30', '07:00', 30, 60, 120, 180, 400),
(34567, 'Duronto Express', 'Chennai', 'Hyderabad', '10:45', '17:30', 25, 55, 110, 160, 350),
(45678, 'Gatimaan Express', 'Delhi', 'Agra', '09:15', '11:00', 35, 70, 140, 200, 450),
(56789, 'Garib Rath', 'Mumbai', 'Pune', '11:30', '13:15', 15, 40, 80, 120, 250),
(67890, 'Jan Shatabdi', 'Bangalore', 'Chennai', '14:15', '17:30', 18, 45, 90, 130, 280),
(78901, 'Tejas Express', 'Goa', 'Mumbai', '07:00', '11:30', 40, 80, 160, 220, 500),
(89012, 'Humsafar Express', 'Jaipur', 'Ahmedabad', '16:20', '20:45', 28, 65, 130, 190, 420),
(90123, 'Superfast Express', 'Kanpur', 'Lucknow', '06:45', '08:30', 22, 48, 95, 140, 320),
(10101, 'Shalimar Express', 'Kolkata', 'Ludhiana', '17:55', '07:45', 32, 70, 140, 200, 430);
select * from user;
select * from admin;
select * from reserve;
select * from train;