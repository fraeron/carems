# Carems — A Car Rental Management System (CRMS) 

<p align="center" style="text-align: center; margin: auto;">
  <img src="https://github.com/fraeron/carems/blob/main/img/carems_logo.png" width=300>
  <img src="https://github.com/fraeron/carems/blob/main/img/gadc_logo.png" width=300>
</p>

This is a 2nd-year undergraduate demo project for the subject Object-Oriented Programming in the course Bachelor of Science in Information Technology (BSIT). 

The system is published by a fictional company named Golden Archer Development Corporation (GADC). This project features a system that offers a comprehensive solution for managing car rentals, ensuring vehicle availability, booking reservations, and maintaining records of car conditions. Our service is designed to provide an affordable and convenient way to access a wide variety without the commitment of purchasing. 

## Code for creating database
Below are the MySQL statements for having the demo database used for this project:
```
create database db_carems

create table tbl_user (
    username varchar(50),
    password varchar(50),
    name varchar(50),
    email varchar(50),
    contact varchar (50)
)

create table tbl_customer (
    id varchar(50),
    name varchar(50),
    drivers_license_id varchar(50),
    credit_card_no varchar(50)
)

create table tbl_car (
    id varchar(50),
    model varchar(50), 
    color varchar(50), 
    license_plate varchar(50), 
    category varchar(50),
    fuel_type varchar(50),
    is_available varchar(50),
    car_condition varchar(50),
    vin varchar(50),
    price_per_day varchar(50),
    engine varchar(10),
    oil varchar(10),
    air varchar(10),
    coolant varchar(10),
    brake varchar(10),
    tire varchar(10),
    belt varchar(10),
    steer varchar(10),
    chassis varchar(10),
    photo_filepath varchar(50)
)

create table tbl_location (
    id varchar(50),
    city varchar(50),
    address varchar(50)
)

create table tbl_book (
    id varchar(50), 
    booked_car_id varchar(50), 
    customer_id varchar(50), 
    booked_datetime varchar(50), 
    return_datetime varchar(50), 
    status varchar(50)
)

insert into tbl_user VALUES 
    ("walterWhite", "Heisenberg", "WALTER H. WHITE", "walter@breakingbad.com", "123456789"),
    ("unSya192", "sleezyRabbit", "Jessie J. Jones", "jessiejessie@hahahaha.com", "123456789"),
    ("RonATT", "ohRon", "Ronald Q. Weasley", "walter@hahahaha.com", "123456789"),
    ("OptimumPride", "autoBots", "Optimum Lakas", "p800085b@optimump.com", "123456789"),
    ("Carems", "OOP", "CAREMS", "SAULGOODMAN@BETTERCALLSAULRAHHHHHHH.com", "123456789")

insert into tbl_customer VALUES 
    ("1", "Raven D. Mcmurray", "A23-52-806752", "2456 1234 9932 0206"),
    ("2", "Kendrick L. Duckworth", "B23-52-806752", "7878 1234 9932 0206"),
    ("3", "Aubrey Graham", "C23-52-806752", "0025 1234 9932 0206"),
    ("4", "Fukuma S. Mizushi", "D23-52-806752", "8450 1234 9932 0206"),
    ("5", "Walter H. White", "E23-52-806752", "9655 1234 9932 0206"),
    ("6", "Jessie J. Jones", "F23-52-806752", "4555 1234 5555 0206"),
    ("7", "Ronald Q. Weasley", "G23-52-806752", "0515 4321 9932 0206"),
    ("8", "Juan P. Delas Santos", "H23-52-806752", "4444 1234 9932 0206"),
    ("9", "Aloe Vera L. Ligaya", "I23-52-806752", "8889 0006 9932 0206")

insert into tbl_car VALUES
    ("1", "Honda Civic", "Orange", "8QRA64", "Sedan", "Unleaded", "Yes", "Good", "2HGFG3B8XEH562177", "1500", "GOOD","GOOD","GOOD","GOOD","GOOD","GOOD","GOOD","GOOD","GOOD", "img/cars/hondacivic.jpg"),
    ("2", "Ford F-250", "Black", "NBC 1234", "Pickup", "Diesel", "Yes", "Good", "1VWBP7A37DC096586", "2000","OK","GOOD","GOOD","GOOD","OK","GOOD","OK","OK","OK", "img/cars/fordf250.jpg"),
    ("3", "Volvo 240", "White", "TOM 369", "Wagon", "Unleaded", "Yes", "OK", "2GTEK133181335795", "1355","GOOD","GOOD","OK","GOOD","GOOD","OK","OK","OK","GOOD", "img/cars/volvo240.jpg"),
    ("4", "DMC DeLorean", "White", "OUTATIME", "Sports", "Unleaded", "No", "Bad", "1GYS3BEF0ER200768", "3000","BAD","GOOD","BAD","BAD","BAD","BAD","BAD","BAD","BAD", "img/cars/dmcdelorean.jpg"),
    ("5", "Audi A4 allroad", "Yellow", "BBC 2134", "Sports", "Unleaded", "No", "Bad", "5P3BC5ED9YE138122", "3000","BAD","GOOD","BAD","GOOD","OK","GOOD","GOOD","GOOD","BAD", "img/cars/audia4allroad.jpeg"),
    ("6", "GMC Acadia", "Green", "LOL 9999", "Sports", "Unleaded", "No", "OK", "2C3BZ5ED9LO138122", "3000","GOOD","GOOD","OK","GOOD","GOOD","OK","OK","OK","GOOD", "img/cars/gmcacadia.jpg"),
    ("7", "Honda Accord", "Black", "6RR509", "Wagon", "Unleaded", "No", "OK", "7O3KE5ED9AN138122", "1500","GOOD","GOOD","OK","GOOD","GOOD","OK","OK","OK","GOOD", "img/cars/hondaaccord.JPG"),
    ("8", "Bentley Bentaga EWB", "Red", "U329OI", "Sedan", "Ethanol", "No", "Good", "5B3BC5ED9AN148122", "2000","OK","GOOD","GOOD","GOOD","OK","GOOD","OK","OK","OK", "img/cars/bentleybentaga.jpg"),
    ("9", "Toyota Corolla Hybrid", "White", "80O84S", "Sedan", "Unleaded", "No", "Good", "1C3BC5RR0AN138122", "1850","OK","GOOD","GOOD","GOOD","OK","GOOD","OK","OK","OK", "img/cars/toyotacorolla.jpg")

insert into tbl_location VALUES 
    ("1", "Biñan City", "Timbao Barangay Hall, Brgy. Timbao, Biñan City, Laguna"),
    ("2", "Raccoon City", "Brgy. Somewhere, Raccoon City"),
    ("3", "City 13", "District #41, Invertus Balespus Street"),
    ("4", "Sta. Rosa City", "House #13, Villa Caceres, Balibago, Sta. Rosa City"),
    ("5", "Biñan City", "PUP Biñan, Brgy. Zapote, Biñan City, Laguna")

insert into tbl_book VALUES 
    ("1", "3", "1", "12/2/2023", "12/10/2023", "RETURNED"),
    ("2", "3", "1", "1/5/2024", "1/6/2024", "RETURNED")

```

## Icon/Image Credits:
- Username Icon (Login) from https://www.shutterstock.com/image-vector
- Lock Icon (Login) from https://pngtree.com/free-png-vectors/padlock
- Car Footer Image (Login) from https://www.pngitem.com/middle/ihmRRwb_car-footer-png-transparent-png/
