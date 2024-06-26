-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2024 at 07:04 PM
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
-- Database: `gestionconge`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID_Admin` int(11) NOT NULL,
  `ID_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chef_administration`
--

CREATE TABLE `chef_administration` (
  `ID_ChefAdmin` int(11) NOT NULL,
  `ID_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chef_departement`
--

CREATE TABLE `chef_departement` (
  `ID_ChefDep` int(11) NOT NULL,
  `Departement` enum('IT','RH','Finance','Sécurité','Datascience','Administration','Marketing') NOT NULL,
  `ID_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `conge`
--

CREATE TABLE `conge` (
  `ID_Conge` int(11) NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `TypeConge` enum('Annuel','Exceptionnel','Maladie','Maternité','Sous_solde') NOT NULL,
  `Statut` enum('Approuvé','Rejeté','En_Attente') NOT NULL,
  `ID_User` int(11) NOT NULL,
  `file` varchar(255) NOT NULL,
  `description` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `conge`
--

INSERT INTO `conge` (`ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description`) VALUES
(89, '2024-06-26', '2024-06-28', 'Annuel', 'Approuvé', 16, '', 'kejroirzjeroz'),
(90, '2024-07-02', '2024-07-17', 'Sous_solde', 'Approuvé', 16, '', 'mjaepzheazioe'),
(91, '2024-08-06', '2024-08-08', 'Annuel', 'Approuvé', 16, '', 'piuhhdz'),
(95, '2024-07-09', '2024-07-18', 'Maladie', 'En_Attente', 16, '23439959-5ee7-42da-88fa-984bc37cd9f7_slim (3).pdf', 'ozapjzaopjjzozjjjjjjjjjjjjjjjjjjjjjjjjjjjkjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk');

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE `employe` (
  `ID_Employé` int(11) NOT NULL,
  `Departement` enum('IT','RH','Finance','Sécurité','Datascience','Administration','Marketing') NOT NULL,
  `ID_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employe`
--

INSERT INTO `employe` (`ID_Employé`, `Departement`, `ID_User`) VALUES
(16, 'Datascience', 16);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `ID_User` int(20) NOT NULL,
  `Nom` varchar(144) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `MDP` varchar(255) NOT NULL,
  `Role` enum('Employé','ChefDepartement','ChefAdministration','AdminIT') NOT NULL,
  `Image` varchar(255) NOT NULL,
  `Solde_Annuel` int(11) NOT NULL DEFAULT 30,
  `Solde_Maladie` int(11) NOT NULL DEFAULT 30,
  `Solde_Exceptionnel` int(11) NOT NULL DEFAULT 30,
  `Solde_Maternité` int(11) NOT NULL DEFAULT 30
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`ID_User`, `Nom`, `Prenom`, `Email`, `MDP`, `Role`, `Image`, `Solde_Annuel`, `Solde_Maladie`, `Solde_Exceptionnel`, `Solde_Maternité`) VALUES
(16, 'Chouaib', 'Slim', '1234', '1234', 'Employé', 'src\\main\\resources\\assets\\imgs\\4d58a9dd-89bc-49c8-b9b2-f1a0d59b5c5f_347577815_6341761682604334_4518175521665308777_n', 1, 30, 30, 30);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID_Admin`),
  ADD KEY `FK_IDUser5` (`ID_User`);

--
-- Indexes for table `chef_administration`
--
ALTER TABLE `chef_administration`
  ADD PRIMARY KEY (`ID_ChefAdmin`),
  ADD KEY `FK_IDUser4` (`ID_User`);

--
-- Indexes for table `chef_departement`
--
ALTER TABLE `chef_departement`
  ADD PRIMARY KEY (`ID_ChefDep`),
  ADD KEY `FK_IDUser3` (`ID_User`);

--
-- Indexes for table `conge`
--
ALTER TABLE `conge`
  ADD PRIMARY KEY (`ID_Conge`),
  ADD KEY `FK_IDUser1` (`ID_User`) USING BTREE;

--
-- Indexes for table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`ID_Employé`),
  ADD KEY `FK_IDUser2` (`ID_User`) USING BTREE;

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`ID_User`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `ID_Admin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `chef_administration`
--
ALTER TABLE `chef_administration`
  MODIFY `ID_ChefAdmin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `chef_departement`
--
ALTER TABLE `chef_departement`
  MODIFY `ID_ChefDep` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conge`
--
ALTER TABLE `conge`
  MODIFY `ID_Conge` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT for table `employe`
--
ALTER TABLE `employe`
  MODIFY `ID_Employé` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID_User` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK_IDUser5` FOREIGN KEY (`ID_User`) REFERENCES `utilisateur` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chef_administration`
--
ALTER TABLE `chef_administration`
  ADD CONSTRAINT `FK_IDUser4` FOREIGN KEY (`ID_User`) REFERENCES `utilisateur` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chef_departement`
--
ALTER TABLE `chef_departement`
  ADD CONSTRAINT `FK_IDUser3` FOREIGN KEY (`ID_User`) REFERENCES `utilisateur` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `conge`
--
ALTER TABLE `conge`
  ADD CONSTRAINT `ID_User_Foreign` FOREIGN KEY (`ID_User`) REFERENCES `utilisateur` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `FK_IDUser` FOREIGN KEY (`ID_User`) REFERENCES `utilisateur` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
