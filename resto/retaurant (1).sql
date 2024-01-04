-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 04 jan. 2024 à 23:00
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `retaurant`
--

-- --------------------------------------------------------

--
-- Structure de la table `drinks`
--

CREATE TABLE `drinks` (
  `numD` int(11) NOT NULL,
  `nameD` varchar(45) NOT NULL,
  `typeD` varchar(45) NOT NULL,
  `costD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `drinks`
--

INSERT INTO `drinks` (`numD`, `nameD`, `typeD`, `costD`) VALUES
(1, 'Coffee', 'Freezy', 18),
(3, 'ColaCola', 'Warm', 43),
(4, 'teaa', 'Warm', 12);

-- --------------------------------------------------------

--
-- Structure de la table `meals`
--

CREATE TABLE `meals` (
  `numM` int(11) NOT NULL,
  `nameM` varchar(45) NOT NULL,
  `typeM` varchar(45) NOT NULL,
  `costM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `meals`
--

INSERT INTO `meals` (`numM`, `nameM`, `typeM`, `costM`) VALUES
(1, 'Spagetti', 'Small', 50),
(2, 'Pizza', 'Medium', 30);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`numD`);

--
-- Index pour la table `meals`
--
ALTER TABLE `meals`
  ADD PRIMARY KEY (`numM`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `drinks`
--
ALTER TABLE `drinks`
  MODIFY `numD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `meals`
--
ALTER TABLE `meals`
  MODIFY `numM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
