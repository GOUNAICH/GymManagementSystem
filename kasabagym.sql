-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 08 avr. 2024 à 00:13
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `kasabagym`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEndDateForAllMembersProcedure` ()   BEGIN
    UPDATE members 
    SET endDate = DATE_ADD(CURDATE(), INTERVAL COALESCE(duree, 0) DAY) 
    WHERE duree > 0 AND startDate = CURDATE();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMemberStatusProcedure` ()   BEGIN
    DECLARE currentDate DATE;
    SET currentDate = CURDATE();

    UPDATE members
    SET status = CASE
        WHEN endDate IS NOT NULL AND endDate < currentDate THEN 'not paid'
        ELSE 'paid'
    END;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', '123');

-- --------------------------------------------------------

--
-- Structure de la table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `duree` int(11) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `numPhone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `members`
--

INSERT INTO `members` (`id`, `nom`, `prenom`, `age`, `gender`, `amount`, `duree`, `endDate`, `startDate`, `status`, `numPhone`) VALUES
(1, 'Doe', 'John', 30, 'M', 50, 30, '2024-05-07', '2024-04-07', 'paid', 123456789),
(2, 'Smith', 'Alice', 25, 'F', 60, 30, '2024-05-07', '2024-04-07', 'paid', 987654321),
(3, 'Brown', 'Emma', 35, 'F', 70, 30, '2024-05-07', '2024-04-07', 'paid', 456123789),
(4, 'Johnson', 'Michael', 40, 'M', 80, 30, '2024-05-07', '2024-04-07', 'paid', 789456123),
(5, 'Williams', 'Olivia', 28, 'F', 55, 30, '2024-05-07', '2024-04-07', 'paid', 321654987),
(6, 'Jones', 'William', 32, 'M', 65, 30, '2024-05-07', '2024-04-07', 'paid', 654789123),
(7, 'Gounaich', 'Abdeslam', 18, 'M', 200, 2, '2024-04-10', '2024-04-08', 'paid', 641559580);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
