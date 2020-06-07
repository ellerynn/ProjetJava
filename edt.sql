-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 06 juin 2020 à 10:53
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
-- Base de données :  `edt`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`ID`, `Nom`) VALUES
(1, 'Web dynamique'),
(2, 'Java'),
(3, 'C++');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `ID_utilisateur` int(11) NOT NULL,
  `ID_cours` int(11) NOT NULL,
  PRIMARY KEY (`ID_utilisateur`,`ID_cours`),
  KEY `ID_cours` (`ID_cours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`ID_utilisateur`, `ID_cours`) VALUES
(2, 1),
(2, 2),
(2, 3),
(16, 1),
(16, 2),
(17, 1),
(17, 3),
(18, 2),
(18, 3);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `ID_utilisateur` int(11) NOT NULL,
  `Numero` varchar(255) NOT NULL,
  `ID_groupe` int(11) NOT NULL,
  PRIMARY KEY (`ID_utilisateur`),
  KEY `ID_groupe` (`ID_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`ID_utilisateur`, `Numero`, `ID_groupe`) VALUES
(4, '0004', 1),
(5, '0005', 1),
(6, '0006', 2),
(7, '0007', 2),
(8, '0008', 3),
(9, '0009', 3),
(10, '0010', 4),
(11, '0011', 4),
(12, '0012', 5),
(13, '0013', 5),
(14, '0014', 6),
(15, '0015', 6);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `ID_promotion` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_promotion` (`ID_promotion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`ID`, `Nom`, `ID_promotion`) VALUES
(1, 'TD01', 1),
(2, 'TD02', 1),
(3, 'TD01', 2),
(4, 'TD02', 2),
(5, 'TD04', 3),
(6, 'TD02', 3);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`ID`, `Nom`) VALUES
(1, 'Ing1'),
(2, 'Ing1'),
(3, 'Ing3');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Capacite` int(11) NOT NULL,
  `ID_site` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_site` (`ID_site`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`ID`, `Nom`, `Capacite`, `ID_site`) VALUES
(1, 'EM001', 15, 1),
(2, 'EM002', 15, 1),
(3, 'P328', 15, 2),
(4, 'P329', 15, 2),
(5, 'GS001', 15, 3),
(6, 'GS002', 15, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Heure_debut` time NOT NULL,
  `Heure_fin` time NOT NULL,
  `Etat` int(11) NOT NULL,
  `ID_cours` int(11) NOT NULL,
  `ID_type` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_cours` (`ID_cours`),
  KEY `ID_type` (`ID_type`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`ID`, `Semaine`, `Date`, `Heure_debut`, `Heure_fin`, `Etat`, `ID_cours`, `ID_type`) VALUES
(1, 20, '2020-05-15', '13:45:00', '15:15:00', 2, 2, 4),
(2, 20, '2020-05-15', '15:30:00', '17:00:00', 2, 2, 4),
(3, 21, '2020-05-18', '12:00:00', '13:30:00', 2, 2, 4),
(4, 22, '2020-05-29', '13:45:00', '15:15:00', 2, 2, 4),
(5, 22, '2020-05-29', '15:30:00', '17:00:00', 2, 2, 4),
(6, 23, '2020-06-05', '13:45:00', '15:15:00', 2, 2, 4),
(7, 23, '2020-06-05', '15:30:00', '17:00:00', 2, 2, 4),
(8, 20, '2020-05-15', '10:15:00', '11:45:00', 2, 2, 2),
(9, 21, '2020-05-18', '15:30:00', '17:00:00', 2, 2, 4),
(10, 21, '2020-05-19', '08:30:00', '10:00:00', 2, 1, 2),
(11, 22, '2020-05-26', '10:00:00', '11:30:00', 1, 1, 2),
(12, 23, '2020-06-02', '10:00:00', '11:30:00', 1, 1, 2),
(13, 20, '2020-05-13', '13:45:00', '15:15:00', 2, 2, 2),
(14, 22, '2020-05-28', '14:30:00', '16:00:00', 2, 2, 2),
(15, 23, '2020-06-03', '14:30:00', '16:00:00', 2, 3, 2),
(16, 22, '2020-05-26', '15:45:00', '17:15:00', 1, 3, 3),
(17, 22, '2020-05-25', '14:00:00', '15:30:00', 2, 1, 1),
(18, 21, '2020-05-19', '10:15:00', '11:45:00', 2, 2, 4),
(19, 22, '2020-05-26', '15:30:00', '17:00:00', 2, 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseignants`
--

DROP TABLE IF EXISTS `seance_enseignants`;
CREATE TABLE IF NOT EXISTS `seance_enseignants` (
  `ID_seance` int(11) NOT NULL,
  `ID_enseignant` int(11) NOT NULL,
  PRIMARY KEY (`ID_seance`,`ID_enseignant`),
  KEY `ID_enseignant` (`ID_enseignant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_enseignants`
--

INSERT INTO `seance_enseignants` (`ID_seance`, `ID_enseignant`) VALUES
(1, 16),
(2, 16),
(3, 16),
(4, 16),
(5, 16),
(6, 16),
(7, 16),
(8, 16),
(9, 16),
(10, 16),
(11, 16),
(12, 16),
(13, 2),
(14, 2),
(15, 2),
(16, 17),
(17, 17),
(18, 18),
(19, 18);

-- --------------------------------------------------------

--
-- Structure de la table `seance_groupes`
--

DROP TABLE IF EXISTS `seance_groupes`;
CREATE TABLE IF NOT EXISTS `seance_groupes` (
  `ID_seance` int(11) NOT NULL,
  `ID_groupe` int(11) NOT NULL,
  PRIMARY KEY (`ID_seance`,`ID_groupe`),
  KEY `ID_groupe` (`ID_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_groupes`
--

INSERT INTO `seance_groupes` (`ID_seance`, `ID_groupe`) VALUES
(1, 5),
(2, 5),
(3, 5),
(4, 5),
(5, 5),
(6, 5),
(7, 5),
(8, 1),
(8, 2),
(9, 2),
(10, 6),
(11, 2),
(12, 1),
(13, 1),
(13, 2),
(14, 1),
(15, 1),
(15, 2),
(16, 1),
(17, 1),
(18, 6),
(19, 2);

-- --------------------------------------------------------

--
-- Structure de la table `seance_salles`
--

DROP TABLE IF EXISTS `seance_salles`;
CREATE TABLE IF NOT EXISTS `seance_salles` (
  `ID_seance` int(11) NOT NULL,
  `ID_salle` int(11) NOT NULL,
  PRIMARY KEY (`ID_seance`,`ID_salle`),
  KEY `ID_salle` (`ID_salle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_salles`
--

INSERT INTO `seance_salles` (`ID_seance`, `ID_salle`) VALUES
(1, 3),
(2, 4),
(3, 3),
(4, 5),
(5, 6),
(6, 3),
(7, 3),
(8, 1),
(8, 2),
(9, 5),
(10, 6),
(13, 1),
(13, 2),
(14, 5),
(15, 1),
(17, 6),
(18, 3),
(19, 5);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`ID`, `Nom`) VALUES
(1, 'Eiffel1'),
(2, 'Eiffel2'),
(3, 'Eiffel4');

-- --------------------------------------------------------

--
-- Structure de la table `type_cours`
--

DROP TABLE IF EXISTS `type_cours`;
CREATE TABLE IF NOT EXISTS `type_cours` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_cours`
--

INSERT INTO `type_cours` (`ID`, `Nom`) VALUES
(1, 'Interactif'),
(2, 'Magistral'),
(3, 'TD'),
(4, 'TP'),
(5, 'TD'),
(6, 'Soutien');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) NOT NULL,
  `Passwd` varchar(255) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Droit` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `Email`, `Passwd`, `Nom`, `Prenom`, `Droit`) VALUES
(1, 'admin@gmail.com', 'admin', 'Admin', 'Admin', 1),
(2, 'segado@edu.ece.fr', 'referent', 'Segado', 'Jean-Pierre', 2),
(3, 'referent2@edu.ece.fr', 'referent', 'Referent2', 'Referent2', 3),
(4, 'etudiant1@edu.ece.fr', 'etudiant', 'Etudiant1', 'Etudiant1', 4),
(5, 'etudiant2@edu.ece.fr', 'etudiant', 'Etudiant2', 'Etudiant2', 4),
(6, 'etudiant3@edu.ece.fr', 'etudiant', 'Etudiant3', 'Etudiant3', 4),
(7, 'etudiant4@edu.ece.fr', 'etudiant', 'Etudiant4', 'Etudiant4', 4),
(8, 'etudiant5@edu.ece.fr', 'etudiant', 'Etudiant5', 'Etudiant5', 4),
(9, 'etudiant6@edu.ece.fr', 'etudiant', 'Etudiant6', 'Etudiant6', 4),
(10, 'etudiant7@edu.ece.fr', 'etudiant', 'Etudiant7', 'Etudiant7', 4),
(11, 'etudiant8@edu.ece.fr', 'etudiant', 'Etudiant8', 'Etudiant8', 4),
(12, 'nous@edu.ece.fr', 'etudiant', 'Nous', 'nous', 4),
(13, 'autre@edu.ece.fr', 'etudiant', 'Autre', 'autre', 4),
(14, 'etudiant11@edu.ece.fr', 'etudiant', 'Etudiant11', 'Etudiant11', 4),
(15, 'etudiant12@edu.ece.fr', 'etudiant', 'Etudiant12', 'Etudiant12', 4),
(16, 'palasi@edu.ece.fr', 'enseignant', 'Palasi', 'Julienne', 3),
(17, 'rendler@edu.ece.fr', 'enseignant', 'Rendler', 'Elisabeth', 3),
(18, 'guan@edu.ece.fr', 'enseignant', 'Guan', 'Henri', 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD CONSTRAINT `enseignant_ibfk_1` FOREIGN KEY (`ID_cours`) REFERENCES `cours` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enseignant_ibfk_2` FOREIGN KEY (`ID_utilisateur`) REFERENCES `utilisateur` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`ID_utilisateur`) REFERENCES `utilisateur` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`ID_groupe`) REFERENCES `groupe` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`ID_promotion`) REFERENCES `promotion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`ID_site`) REFERENCES `site` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`ID_cours`) REFERENCES `cours` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seance_ibfk_2` FOREIGN KEY (`ID_type`) REFERENCES `type_cours` (`ID`);

--
-- Contraintes pour la table `seance_enseignants`
--
ALTER TABLE `seance_enseignants`
  ADD CONSTRAINT `seance_enseignants_ibfk_1` FOREIGN KEY (`ID_enseignant`) REFERENCES `enseignant` (`ID_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seance_enseignants_ibfk_2` FOREIGN KEY (`ID_seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `seance_groupes`
--
ALTER TABLE `seance_groupes`
  ADD CONSTRAINT `seance_groupes_ibfk_1` FOREIGN KEY (`ID_groupe`) REFERENCES `groupe` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seance_groupes_ibfk_2` FOREIGN KEY (`ID_seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `seance_salles`
--
ALTER TABLE `seance_salles`
  ADD CONSTRAINT `seance_salles_ibfk_1` FOREIGN KEY (`ID_salle`) REFERENCES `salle` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seance_salles_ibfk_2` FOREIGN KEY (`ID_seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
