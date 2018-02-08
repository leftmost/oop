-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 08, 2018 alle 18:44
-- Versione del server: 10.1.28-MariaDB
-- Versione PHP: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
-- Struttura della tabella `gioco`
--

DROP TABLE IF EXISTS `gioco`;
CREATE TABLE `gioco` (
  `id` int(11) NOT NULL,
  `Titolo` varchar(45) NOT NULL,
  `Valutazione` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `gioco`
--

INSERT INTO `gioco` (`id`, `Titolo`, `Valutazione`) VALUES
(1, 'Burraco', 0),
(2, 'Cirulla', 0),
(3, 'Hearts', 0),
(4, 'Scopa', 0),
(5, 'Briscola', 0),
(6, 'Bestia', 0),
(7, 'Poker', 0),
(8, 'bobo', 0),
(9, 'Solitario', 0),
(10, 'Teresina', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `recensione`
--

DROP TABLE IF EXISTS `recensione`;
CREATE TABLE `recensione` (
  `Utente_Username` varchar(15) NOT NULL,
  `Gioco_id` int(11) NOT NULL,
  `Approvazione` tinyint(4) NOT NULL DEFAULT '0',
  `Voto` int(1) NOT NULL DEFAULT '0',
  `Recensione` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Trigger `recensione`
--
DROP TRIGGER IF EXISTS `Recensione_AFTER_UPDATE`;
DELIMITER $$
CREATE TRIGGER `Recensione_AFTER_UPDATE` AFTER UPDATE ON `recensione` FOR EACH ROW BEGIN
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
-- Struttura della tabella `timeline`
--

DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline` (
  `Utente_Username` varchar(15) NOT NULL,
  `Data` date NOT NULL,
  `Exp` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `timeline`
--

INSERT INTO `timeline` (`Utente_Username`, `Data`, `Exp`) VALUES
('andcant', '2018-02-08', 0),
('loreand', '2018-02-08', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

DROP TABLE IF EXISTS `utente`;
CREATE TABLE `utente` (
  `Username` varchar(15) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Tipologia` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`Username`, `Email`, `Password`, `Nome`, `Cognome`, `Tipologia`) VALUES
('andcant', 'andcant@lib.it', '1234', 'and', 'cant', 'Admin'),
('loreand', 'lor@and.it', '1234', 'lor', 'and', 'Admin');

--
-- Trigger `utente`
--
DROP TRIGGER IF EXISTS `Utente_AFTER_INSERT`;
DELIMITER $$
CREATE TRIGGER `Utente_AFTER_INSERT` AFTER INSERT ON `utente` FOR EACH ROW BEGIN
INSERT INTO Timeline(Utente_Username,Data,Exp) VALUES(NEW.Username,CURRENT_DATE,'0');
END
$$
DELIMITER ;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `gioco`
--
ALTER TABLE `gioco`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `recensione`
--
ALTER TABLE `recensione`
  ADD PRIMARY KEY (`Utente_Username`,`Gioco_id`),
  ADD KEY `fk_Recensione_Gioco1_idx` (`Gioco_id`);

--
-- Indici per le tabelle `timeline`
--
ALTER TABLE `timeline`
  ADD PRIMARY KEY (`Utente_Username`,`Data`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`Username`),
  ADD UNIQUE KEY `Username_UNIQUE` (`Username`),
  ADD UNIQUE KEY `Email_UNIQUE` (`Email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `gioco`
--
ALTER TABLE `gioco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `recensione`
--
ALTER TABLE `recensione`
  ADD CONSTRAINT `fk_Recensione_Gioco1` FOREIGN KEY (`Gioco_id`) REFERENCES `gioco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Recensione_Utente1` FOREIGN KEY (`Utente_Username`) REFERENCES `utente` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `timeline`
--
ALTER TABLE `timeline`
  ADD CONSTRAINT `fk_Timeline_Utente` FOREIGN KEY (`Utente_Username`) REFERENCES `utente` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;