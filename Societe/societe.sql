-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 18 mai 2020 à 10:41
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `societe`
--

-- --------------------------------------------------------

--
-- Structure de la table `developpeur`
--

DROP TABLE IF EXISTS `developpeur`;
CREATE TABLE IF NOT EXISTS `developpeur` (
  `dev_id` int(11) NOT NULL AUTO_INCREMENT,
  `dev_nom` varchar(64) DEFAULT NULL,
  `dev_prenom` varchar(64) DEFAULT NULL,
  `dev_lan_k` int(11) NOT NULL,
  PRIMARY KEY (`dev_id`),
  KEY `dev_lan_k` (`dev_lan_k`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `developpeur`
--

INSERT INTO `developpeur` (`dev_id`, `dev_nom`, `dev_prenom`, `dev_lan_k`) VALUES
(1, 'HERBY', 'Cyrille', 1),
(2, 'PITON', 'Thomas', 3),
(3, 'COURTEL', 'Angelo', 2);

-- --------------------------------------------------------

--
-- Structure de la table `j_soc_dev`
--

DROP TABLE IF EXISTS `j_soc_dev`;
CREATE TABLE IF NOT EXISTS `j_soc_dev` (
  `jsd_id` int(11) NOT NULL AUTO_INCREMENT,
  `jsd_soc_k` int(11) NOT NULL,
  `jsd_dev_k` int(11) NOT NULL,
  PRIMARY KEY (`jsd_id`),
  KEY `jsd_dev_k` (`jsd_dev_k`),
  KEY `jsd_soc_k` (`jsd_soc_k`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `j_soc_dev`
--

INSERT INTO `j_soc_dev` (`jsd_id`, `jsd_soc_k`, `jsd_dev_k`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 1),
(5, 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `langage`
--

DROP TABLE IF EXISTS `langage`;
CREATE TABLE IF NOT EXISTS `langage` (
  `lan_id` int(11) NOT NULL AUTO_INCREMENT,
  `lan_nom` varchar(64) NOT NULL,
  PRIMARY KEY (`lan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `langage`
--

INSERT INTO `langage` (`lan_id`, `lan_nom`) VALUES
(1, 'Java'),
(2, 'PHP'),
(3, 'C++');

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

DROP TABLE IF EXISTS `societe`;
CREATE TABLE IF NOT EXISTS `societe` (
  `soc_id` int(11) NOT NULL AUTO_INCREMENT,
  `soc_nom` varchar(64) NOT NULL,
  PRIMARY KEY (`soc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `societe`
--

INSERT INTO `societe` (`soc_id`, `soc_nom`) VALUES
(1, 'Societe 1'),
(2, 'Societe 2');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `developpeur`
--
ALTER TABLE `developpeur`
  ADD CONSTRAINT `developpeur_ibfk_1` FOREIGN KEY (`dev_lan_k`) REFERENCES `langage` (`lan_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `j_soc_dev`
--
ALTER TABLE `j_soc_dev`
  ADD CONSTRAINT `j_soc_dev_ibfk_1` FOREIGN KEY (`jsd_dev_k`) REFERENCES `developpeur` (`dev_id`),
  ADD CONSTRAINT `j_soc_dev_ibfk_2` FOREIGN KEY (`jsd_soc_k`) REFERENCES `societe` (`soc_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
