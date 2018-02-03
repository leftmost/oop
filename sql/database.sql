-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Feb 03, 2018 alle 23:03
-- Versione del server: 5.6.35
-- Versione PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oop`
--
CREATE DATABASE IF NOT EXISTS `oop` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oop`;

-- --------------------------------------------------------

--
-- Struttura della tabella `Gioco`
--

DROP TABLE IF EXISTS `Gioco`;
CREATE TABLE `Gioco` (
  `id` int(11) NOT NULL,
  `Titolo` varchar(45) NOT NULL,
  `Exp` int(11) NOT NULL,
  `Valutazione` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Gioco`
--

INSERT INTO `Gioco` (`id`, `Titolo`, `Exp`, `Valutazione`) VALUES
(0, 'COD', 0, 0),
(1, 'GTA', 0, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `Recensione`
--

DROP TABLE IF EXISTS `Recensione`;
CREATE TABLE `Recensione` (
  `Utente_Username` varchar(15) NOT NULL,
  `Gioco_id` int(11) NOT NULL,
  `Approvazione` tinyint(4) NOT NULL DEFAULT '0',
  `Voto` int(1) NOT NULL DEFAULT '0',
  `Recensione` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Recensione`
--

INSERT INTO `Recensione` (`Utente_Username`, `Gioco_id`, `Approvazione`, `Voto`, `Recensione`) VALUES
('andcant', 1, 1, 5, ''),
('loreand', 0, 0, 5, ''),
('loreand', 1, 1, 5, '');

--
-- Trigger `Recensione`
--
DROP TRIGGER IF EXISTS `Recensione_AFTER_UPDATE`;
DELIMITER $$
CREATE TRIGGER `Recensione_AFTER_UPDATE` AFTER UPDATE ON `Recensione` FOR EACH ROW BEGIN
	IF NEW.Approvazione=1
	THEN
		UPDATE Gioco 
        SET Valutazione=((SELECT SUM(Voto) From Recensione WHERE Gioco_id=NEW.Gioco_id)/(SELECT COUNT(Voto) From Recensione WHERE Gioco_id=NEW.Gioco_id)) WHERE id=NEW.Gioco_id;
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Timeline`
--

DROP TABLE IF EXISTS `Timeline`;
CREATE TABLE `Timeline` (
  `Utente_Username` varchar(15) NOT NULL,
  `Livello` int(1) NOT NULL DEFAULT '0',
  `Data` date NOT NULL,
  `Exp` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Timeline`
--

INSERT INTO `Timeline` (`Utente_Username`, `Livello`, `Data`, `Exp`) VALUES
('alepapa', 0, '2018-02-02', 0),
('andcant', 0, '2018-01-30', 0),
('loreand', 0, '2018-01-30', 0),
('loreand', 0, '2018-02-01', 10),
('matross', 0, '2018-01-30', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `Utente`
--

DROP TABLE IF EXISTS `Utente`;
CREATE TABLE `Utente` (
  `Username` varchar(15) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Tipologia` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Utente`
--

INSERT INTO `Utente` (`Username`, `Email`, `Password`, `Nome`, `Cognome`, `Tipologia`) VALUES
('alepapa', 'papaleo@univaq.it', '1234', 'Alessandro', 'Papapleo', 'Utente'),
('andcant', 'cantagallo@univaq.it', '1234', 'Andrea', 'Cantagallo', 'Admin'),
('loreand', 'andreoli@univaq.it', '1234', 'Lorenzo', 'Andreoli', 'Admin'),
('matross', 'rossi@univaq.it', '1234', 'Mattia', 'Rossi', 'Moderator');

--
-- Trigger `Utente`
--
DROP TRIGGER IF EXISTS `Utente_AFTER_INSERT`;
DELIMITER $$
CREATE TRIGGER `Utente_AFTER_INSERT` AFTER INSERT ON `Utente` FOR EACH ROW BEGIN
INSERT INTO Timeline(Utente_Username,Livello,Data,Exp) VALUES(NEW.Username,'0',CURRENT_DATE,'0');
END
$$
DELIMITER ;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `Gioco`
--
ALTER TABLE `Gioco`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Recensione`
--
ALTER TABLE `Recensione`
  ADD PRIMARY KEY (`Utente_Username`,`Gioco_id`),
  ADD KEY `fk_Recensione_Gioco1_idx` (`Gioco_id`);

--
-- Indici per le tabelle `Timeline`
--
ALTER TABLE `Timeline`
  ADD PRIMARY KEY (`Utente_Username`,`Livello`,`Data`);

--
-- Indici per le tabelle `Utente`
--
ALTER TABLE `Utente`
  ADD PRIMARY KEY (`Username`),
  ADD UNIQUE KEY `Username_UNIQUE` (`Username`),
  ADD UNIQUE KEY `Email_UNIQUE` (`Email`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Recensione`
--
ALTER TABLE `Recensione`
  ADD CONSTRAINT `fk_Recensione_Gioco1` FOREIGN KEY (`Gioco_id`) REFERENCES `Gioco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Recensione_Utente1` FOREIGN KEY (`Utente_Username`) REFERENCES `Utente` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Timeline`
--
ALTER TABLE `Timeline`
  ADD CONSTRAINT `fk_Timeline_Utente` FOREIGN KEY (`Utente_Username`) REFERENCES `Utente` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
