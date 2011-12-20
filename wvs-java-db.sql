-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 20. Dez 2011 um 23:33
-- Server Version: 5.5.16
-- PHP-Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `wvs-java-db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `adressen`
--

DROP TABLE IF EXISTS `adressen`;
CREATE TABLE IF NOT EXISTS `adressen` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `anrede` varchar(4) NOT NULL,
  `vorname` varchar(40) NOT NULL,
  `nachname` varchar(40) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  `plz` int(5) NOT NULL,
  `ort` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Daten für Tabelle `adressen`
--

INSERT INTO `adressen` (`id`, `anrede`, `vorname`, `nachname`, `adresse`, `plz`, `ort`) VALUES
(1, 'Herr', 'Wolfgang', 'Müller', 'Baumgasse 38', 1030, 'Köln'),
(2, 'Herr', 'Max', 'Mustermann', 'Musterstraße 44', 1010, 'Köln'),
(3, 'Frau', 'Kai', 'Meier', 'Beispielstraße 08', 4020, 'Düsseldorf'),
(4, 'Frau', 'Kim', 'Meier', 'Demogasse 15', 5020, 'Köln'),
(5, 'Herr', 'Christoph', 'Hausgeburt', 'Demogasse 1', 6060, 'Düsseldorf'),
(6, 'Frau', 'Berta', 'Namenichtecht', 'Demogasse 4', 2500, 'Düsseldorf');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
