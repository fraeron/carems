# Carems — A Car Rental Management System (CRMS) 

<p align="center" style="text-align: center; margin: auto;">
  <img src="https://github.com/fraeron/carems/blob/main/img/carems_logo.png" width=300>
  <img src="https://github.com/fraeron/carems/blob/main/img/gadc_logo.png" width=300>
</p>

This is a 2nd-year undergraduate demo project for the subject Object-Oriented Programming in the course Bachelor of Science in Information Technology (BSIT). 

The system is published by a fictional company named Golden Archer Development Corporation (GADC). This project features a system that offers a comprehensive solution for managing car rentals, ensuring vehicle availability, booking reservations, and maintaining records of car conditions. Our service is designed to provide an affordable and convenient way to access a wide variety without the commitment of purchasing. 

## Code for creating database
```
create database db_carems

create table tbl_user (
    username varchar(50),
    password varchar(50),
    name varchar(50),
    email varchar(50),
    contact varchar(50)
)

create table tbl_customer (
    id varchar(50),
    name varchar(50),
    rented_car_id varchar(50)
)

create table tbl_car (
    model varchar(50), 
    color varchar(50), 
    license_plate varchar(50), 
    category varchar(50),
    fuel_type varchar(50),
    is_available varchar(50),
    car_condition varchar(50)
)

create table tbl_owner (
    id varchar(50),
    name varchar(50),
    car varchar(50)
)

create table tbl_book (
    id varchar(50), 
    booked_car_id varchar(50), 
    customer_id varchar(50), 
    booked_datetime varchar(50), 
    return_datetime varchar(50), 
    status_datetime varchar(50)
)

insert into tbl_user (username, password) VALUES 
    ("walterWhite", "Heisenberg"),
    ("unSya192", "sleezyRabbit"),
    ("RonATT", "ohRon"),
    ("OptimumPride", "autoBots"),
    ("Carems", "OOP")

insert into tbl_customer VALUES 
    ("1", "Raven D. Mcmurray", "1"),
    ("2", "Kendrick L. Duckworth", "2"),
    ("3", "Aubrey Graham", "3"),
    ("4", "Fukuma S. Mizushi", "4")


insert into tbl_car VALUES
    ("Honda Civic", "Orange", "8QRA64", "Sedan", "Unleaded", "Yes", "Good"),
    ("Ford F-250", "Black", "NBC 1234", "Pickup", "Diesel", "Yes", "Good"),
    ("Volvo 240", "White", "TOM 369", "Wagon", "Unleaded", "Yes", "OK"),
    ("DMC DeLorean", "White", "OUTATIME", "Sports", "Unleaded", "No", "Bad")


insert into tbl_owner VALUES 
    ("1", "Miguel O. Harem", "Honda Civic"),
    ("2", "Michael Gyatt Sigma", "Ford F-250"),
    ("3", "Gaylord Batumbakal", "Volvo 240"),
    ("4", "Felisha M. Macawala", "DMC DeLorean")

insert into tbl_book VALUES 
    ("1", "3", "1", "12/2/2023", "12/10/2023", "RETURNED"),
    ("2", "3", "1", "1/5/2024", "1/6/2024", "RETURNED")

```

## Icon/Image Credits:
- Username Icon (Login) from https://www.shutterstock.com/image-vector
- Lock Icon (Login) from https://pngtree.com/free-png-vectors/padlock
- Car Footer Image (Login) from https://www.pngitem.com/middle/ihmRRwb_car-footer-png-transparent-png/
