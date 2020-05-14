-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 14 mai 2020 à 15:12
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`ID`, `Nom`) VALUES
(1, 'Web dynamique'),
(2, 'Analyse'),
(3, 'Thermodynamique'),
(4, 'Java'),
(5, 'Statistiques'),
(6, 'Traitement de signal');

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
(16, 1),
(16, 4),
(17, 2),
(17, 5),
(18, 3),
(18, 6);

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
(5, 'TD01', 3),
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
(1, '2021'),
(2, '2022'),
(3, '2023');

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
(1, '001', 15, 1),
(2, '002', 15, 1),
(3, '001', 15, 2),
(4, '002', 15, 2),
(5, '001', 15, 3),
(6, '002', 15, 3);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseigants`
--

DROP TABLE IF EXISTS `seance_enseigants`;
CREATE TABLE IF NOT EXISTS `seance_enseigants` (
  `ID_seance` int(11) NOT NULL,
  `ID_enseignant` int(11) NOT NULL,
  PRIMARY KEY (`ID_seance`,`ID_enseignant`),
  KEY `ID_enseignant` (`ID_enseignant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(1, 'Eiffel 1'),
(2, 'Eiffel 2'),
(3, 'Eiffel 3');

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
(5, 'Projet'),
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
(2, 'referent1@edu.ece.fr', 'referent', 'Referent1', 'Referent1', 2),
(3, 'referent2@edu.ece.fr', 'referent', 'Referent2', 'Referent2', 3),
(4, 'etudiant1@edu.ece.fr', 'etudiant', 'Etudiant1', 'Etudiant1', 5),
(5, 'etudiant2@edu.ece.fr', 'etudiant', 'Etudiant2', 'Etudiant2', 4),
(6, 'etudiant3@edu.ece.fr', 'etudiant', 'Etudiant3', 'Etudiant3', 4),
(7, 'etudiant4@edu.ece.fr', 'etudiant', 'Etudiant4', 'Etudiant4', 4),
(8, 'etudiant5@edu.ece.fr', 'etudiant', 'Etudiant5', 'Etudiant5', 4),
(9, 'etudiant6@edu.ece.fr', 'etudiant', 'Etudiant6', 'Etudiant6', 4),
(10, 'etudiant7@edu.ece.fr', 'etudiant', 'Etudiant7', 'Etudiant7', 4),
(11, 'etudiant8@edu.ece.fr', 'etudiant', 'Etudiant8', 'Etudiant8', 4),
(12, 'etudiant9@edu.ece.fr', 'etudiant', 'Etudiant9', 'Etudiant9', 4),
(13, 'etudiant10@edu.ece.fr', 'etudiant', 'Etudiant10', 'Etudiant10', 4),
(14, 'etudiant11@edu.ece.fr', 'etudiant', 'Etudiant11', 'Etudiant11', 4),
(15, 'etudiant12@edu.ece.fr', 'etudiant', 'Etudiant12', 'Etudiant12', 4),
(16, 'enseignant1@ece.edu.fr', 'enseignant', 'Enseignant1', 'Enseignant1', 3),
(17, 'enseignant2@ece.edu.fr', 'enseignant', 'Enseignant2', 'Enseignant2', 3),
(18, 'enseignant3@edu.ece.fr', 'enseignant', 'Enseignant3', 'Enseignant3', 3);

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
-- Contraintes pour la table `seance_enseigants`
--
ALTER TABLE `seance_enseigants`
  ADD CONSTRAINT `seance_enseigants_ibfk_1` FOREIGN KEY (`ID_enseignant`) REFERENCES `enseignant` (`ID_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seance_enseigants_ibfk_2` FOREIGN KEY (`ID_seance`) REFERENCES `seance` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

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
