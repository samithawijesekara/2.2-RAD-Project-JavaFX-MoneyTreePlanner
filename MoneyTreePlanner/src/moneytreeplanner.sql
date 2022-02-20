-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 01, 2021 at 04:30 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moneytreeplanner`
--

-- --------------------------------------------------------

--
-- Table structure for table `fixeddeposits`
--

CREATE TABLE `fixeddeposits` (
  `id` int(11) NOT NULL,
  `bankName` text NOT NULL,
  `accountNo` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fixeddeposits`
--

INSERT INTO `fixeddeposits` (`id`, `bankName`, `accountNo`, `amount`) VALUES
(1, 'Sampath Bank', 3434343, 10050000),
(2, 'Commercial Bank', 433434, 10080000);

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

CREATE TABLE `income` (
  `id` int(11) NOT NULL,
  `sourceName` text NOT NULL,
  `amount` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`id`, `sourceName`, `amount`, `description`) VALUES
(1, 'Samitha', 4234324, 'csadsad'),
(3, 'sadasd', 32323, 'csdds'),
(4, 'fsdffds', 423, 'fdsfds'),
(5, 'sadasd', 54535, 'csdds'),
(6, 'sadasd', 32323, 'csdds');

-- --------------------------------------------------------

--
-- Table structure for table `outgoing`
--

CREATE TABLE `outgoing` (
  `id` int(11) NOT NULL,
  `sourceName` text NOT NULL,
  `amount` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `outgoing`
--

INSERT INTO `outgoing` (`id`, `sourceName`, `amount`, `description`) VALUES
(1, 'dsad', 435453, 'dsada'),
(2, 'dsad', 10000000, 'dsada'),
(3, 'dsad', 435453, 'dsada'),
(4, 'dsad', 435453, 'dsada'),
(5, 'dsad', 435453, 'dsada'),
(7, 'dsad', 435453, 'dsada');

-- --------------------------------------------------------

--
-- Table structure for table `profiledetails`
--

CREATE TABLE `profiledetails` (
  `firstName` text NOT NULL,
  `LastName` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profiledetails`
--

INSERT INTO `profiledetails` (`firstName`, `LastName`, `password`) VALUES
('User', 'User', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `savings`
--

CREATE TABLE `savings` (
  `id` int(11) NOT NULL,
  `bankName` text NOT NULL,
  `accountNo` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `savings`
--

INSERT INTO `savings` (`id`, `bankName`, `accountNo`, `amount`) VALUES
(2, 'DFCC Bank', 434, 434234),
(3, 'Commercial Bank', 343, 432),
(4, 'Sampath Bank', 3231323, 324323),
(5, 'BOC', 543534, 54765576);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fixeddeposits`
--
ALTER TABLE `fixeddeposits`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `outgoing`
--
ALTER TABLE `outgoing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `savings`
--
ALTER TABLE `savings`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fixeddeposits`
--
ALTER TABLE `fixeddeposits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `income`
--
ALTER TABLE `income`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `outgoing`
--
ALTER TABLE `outgoing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `savings`
--
ALTER TABLE `savings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
