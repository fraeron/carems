-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2024 at 03:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_carems`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_book`
--

CREATE TABLE `tbl_book` (
  `id` varchar(50) DEFAULT NULL,
  `booked_car_id` varchar(50) DEFAULT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `booked_datetime` varchar(50) DEFAULT NULL,
  `return_datetime` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_book`
--

INSERT INTO `tbl_book` (`id`, `booked_car_id`, `customer_id`, `booked_datetime`, `return_datetime`, `status`) VALUES
('1', '2', '2', '24-06-2024', '25-06-2024', 'LATE RETURNED'),
('2', '3', '3', '05-06-2024', '11-06-2024', 'RETURNED'),
('4', '5', '6', '12-06-2024', '17-06-2024', 'RETURNED'),
('6', '3', '8', '19-06-2024', '10-06-2024', 'RETURNED'),
('7', '6', '8', '12-06-2024', '15-06-2024', 'LATE RETURNED'),
('8', '8', '5', '13-06-2024', '14-06-2024', 'LATE RETURNED');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_car`
--

CREATE TABLE `tbl_car` (
  `id` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `license_plate` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `fuel_type` varchar(50) DEFAULT NULL,
  `is_available` varchar(50) DEFAULT NULL,
  `car_condition` varchar(50) DEFAULT NULL,
  `vin` varchar(50) DEFAULT NULL,
  `price_per_day` varchar(50) DEFAULT NULL,
  `engine` varchar(10) DEFAULT NULL,
  `oil` varchar(10) DEFAULT NULL,
  `air` varchar(10) DEFAULT NULL,
  `coolant` varchar(10) DEFAULT NULL,
  `brake` varchar(10) DEFAULT NULL,
  `tire` varchar(10) DEFAULT NULL,
  `belt` varchar(10) DEFAULT NULL,
  `steer` varchar(10) DEFAULT NULL,
  `chassis` varchar(10) DEFAULT NULL,
  `photo_filepath` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_car`
--

INSERT INTO `tbl_car` (`id`, `model`, `color`, `license_plate`, `category`, `fuel_type`, `is_available`, `car_condition`, `vin`, `price_per_day`, `engine`, `oil`, `air`, `coolant`, `brake`, `tire`, `belt`, `steer`, `chassis`, `photo_filepath`) VALUES
('1', 'Honda Civic', 'Orange', '8QRA64', 'Sedan', 'Unleaded', 'Yes', 'Good', '2HGFG3B8XEH562177', '1500', 'GOOD', 'GOOD', 'GOOD', 'GOOD', 'GOOD', 'GOOD', 'GOOD', 'GOOD', 'GOOD', 'img/cars/hondacivic.jpg'),
('2', 'Ford F-250', 'Black', 'NBC 1234', 'Pickup', 'Diesel', 'Yes', 'Good', '1VWBP7A37DC096586', '2000', 'OK', 'GOOD', 'GOOD', 'GOOD', 'OK', 'GOOD', 'OK', 'OK', 'OK', 'img/cars/fordf250.jpg'),
('3', 'Volvo 240', 'White', 'TOM 369', 'Wagon', 'Unleaded', 'Yes', 'OK', '2GTEK133181335795', '1355', 'GOOD', 'GOOD', 'OK', 'GOOD', 'GOOD', 'OK', 'OK', 'OK', 'GOOD', 'img/cars/volvo240.jpg'),
('4', 'DMC DeLorean', 'White', 'OUTATIME', 'Sports', 'Unleaded', 'No', 'Bad', '1GYS3BEF0ER200768', '3000', 'BAD', 'GOOD', 'BAD', 'BAD', 'BAD', 'BAD', 'BAD', 'BAD', 'BAD', 'img/cars/dmcdelorean.png'),
('5', 'Audi A4 allroad', 'Yellow', 'BBC 2134', 'Sports', 'Unleaded', 'No', 'Bad', '5P3BC5ED9YE138122', '3000', 'BAD', 'GOOD', 'BAD', 'GOOD', 'OK', 'GOOD', 'GOOD', 'GOOD', 'BAD', 'img/cars/audia4allroad.jpeg'),
('6', 'GMC Acadia', 'Green', 'LOL 9999', 'Sports', 'Unleaded', 'No', 'OK', '2C3BZ5ED9LO138122', '3000', 'GOOD', 'GOOD', 'OK', 'GOOD', 'GOOD', 'OK', 'OK', 'OK', 'GOOD', 'img/cars/gmcacadia.jpg'),
('7', 'Honda Accord', 'Black', '6RR509', 'Wagon', 'Unleaded', 'No', 'OK', '7O3KE5ED9AN138122', '1500', 'GOOD', 'GOOD', 'OK', 'GOOD', 'GOOD', 'OK', 'OK', 'OK', 'GOOD', 'img/cars/hondaaccord.JPG'),
('8', 'Bentley Bentaga EWB', 'Red', 'U329OI', 'Sedan', 'Ethanol', 'No', 'Good', '5B3BC5ED9AN148122', '2000', 'OK', 'GOOD', 'GOOD', 'GOOD', 'OK', 'GOOD', 'OK', 'OK', 'OK', 'img/cars/bentleybentaga.jpg'),
('9', 'Toyota Corolla Hybrid', 'White', '80O84S', 'Sedan', 'Unleaded', 'No', 'Good', '1C3BC5RR0AN138122', '1850', 'OK', 'GOOD', 'GOOD', 'GOOD', 'OK', 'GOOD', 'OK', 'OK', 'OK', 'img/cars/toyotacorolla.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `id` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `drivers_license_id` varchar(50) DEFAULT NULL,
  `credit_card_no` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_customer`
--

INSERT INTO `tbl_customer` (`id`, `name`, `drivers_license_id`, `credit_card_no`) VALUES
('1', 'Raven D. Mcmurray', 'A23-52-806752', '2456 1234 9932 0206'),
('2', 'Kendrick L. Duckworth', 'B23-52-806752', '7878 1234 9932 0206'),
('3', 'Aubrey Graham', 'C23-52-806752', '0025 1234 9932 0206'),
('4', 'Fukuma S. Mizushi', 'D23-52-806752', '8450 1234 9932 0206'),
('5', 'Walter H. White', 'E23-52-806752', '9655 1234 9932 0206'),
('7', 'Ronald Q. Weasley', 'G23-52-806752', '0515 4321 9932 0206'),
('8', 'Juan P. Delas Santos', 'H23-52-806752', '4444 1234 9932 0206'),
('9', 'Aloe Vera L. Ligaya', 'I23-52-806752', '8889 0006 9932 0206');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_location`
--

CREATE TABLE `tbl_location` (
  `id` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_location`
--

INSERT INTO `tbl_location` (`id`, `city`, `address`) VALUES
('1', 'Biñan City', 'Timbao Barangay Hall, Brgy. Timbao, Biñan City, La'),
('2', 'Raccoon City', 'Brgy. Somewhere, Raccoon City'),
('3', 'City 13', 'District #41, Invertus Balespus Street'),
('4', 'Sta. Rosa City', 'House #13, Villa Caceres, Balibago, Sta. Rosa City'),
('5', 'Biñan City', 'PUP Biñan, Brgy. Zapote, Biñan City, Laguna');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`username`, `password`, `name`, `email`, `contact`) VALUES
('walterWhite', 'Heisenberg', 'WALTER H. WHITE', 'walter@breakingbad.com', '123456789'),
('unSya192', 'sleezyRabbit', 'Jessie J. Jones', 'jessiejessie@hahahaha.com', '123456789'),
('RonATT', 'ohRon', 'Ronald Q. Weasley', 'walter@hahahaha.com', '123456789'),
('OptimumPride', 'autoBots', 'Optimum Lakas', 'p800085b@optimump.com', '123456789'),
('Carems', 'OOP', 'CAREMS', 'SAULGOODMAN@BETTERCALLSAULRAHHHHHHH.com', '123456789');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
