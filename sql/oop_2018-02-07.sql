# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.6.38)
# Database: oop
# Generation Time: 2018-02-07 16:06:13 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Gioco
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Gioco`;

CREATE TABLE `Gioco` (
  `id` int(11) NOT NULL,
  `Titolo` varchar(45) NOT NULL,
  `Valutazione` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Gioco` WRITE;
/*!40000 ALTER TABLE `Gioco` DISABLE KEYS */;

INSERT INTO `Gioco` (`id`, `Titolo`, `Valutazione`)
VALUES
	(0,'Solitario',0),
	(1,'Burraco',0),
	(2,'Cirulla',0),
	(3,'Hearts',0),
	(4,'Scopa',0),
	(5,'Briscola',0),
	(6,'Bestia',0),
	(7,'Poker',0);

/*!40000 ALTER TABLE `Gioco` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Recensione
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Recensione`;

CREATE TABLE `Recensione` (
  `Utente_Username` varchar(15) NOT NULL,
  `Gioco_id` int(11) NOT NULL,
  `Approvazione` tinyint(4) NOT NULL DEFAULT '0',
  `Voto` int(1) NOT NULL DEFAULT '0',
  `Recensione` text NOT NULL,
  PRIMARY KEY (`Utente_Username`,`Gioco_id`),
  KEY `fk_Recensione_Gioco1_idx` (`Gioco_id`),
  CONSTRAINT `fk_Recensione_Gioco1` FOREIGN KEY (`Gioco_id`) REFERENCES `Gioco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Recensione_Utente1` FOREIGN KEY (`Utente_Username`) REFERENCES `Utente` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="NO_AUTO_VALUE_ON_ZERO" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`root`@`localhost` */ /*!50003 TRIGGER `Recensione_AFTER_UPDATE` AFTER UPDATE ON `Recensione` FOR EACH ROW BEGIN
	IF NEW.Approvazione=1
	THEN
		UPDATE Gioco 
        SET Valutazione=((SELECT SUM(Voto) From Recensione WHERE Gioco_id=NEW.Gioco_id)/(SELECT COUNT(Voto) From Recensione WHERE Gioco_id=NEW.Gioco_id)) WHERE id=NEW.Gioco_id;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Timeline
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Timeline`;

CREATE TABLE `Timeline` (
  `Utente_Username` varchar(15) NOT NULL,
  `Data` date NOT NULL,
  `Exp` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Utente_Username`,`Data`),
  CONSTRAINT `fk_Timeline_Utente` FOREIGN KEY (`Utente_Username`) REFERENCES `Utente` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Timeline` WRITE;
/*!40000 ALTER TABLE `Timeline` DISABLE KEYS */;

INSERT INTO `Timeline` (`Utente_Username`, `Data`, `Exp`)
VALUES
	('alepapa','2018-02-02',0),
	('andcant','2018-01-30',0),
	('giulia','2018-02-07',0),
	('loreand','2018-01-30',0),
	('loreand','2018-02-01',10),
	('matross','2018-01-30',0);

/*!40000 ALTER TABLE `Timeline` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Utente
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Utente`;

CREATE TABLE `Utente` (
  `Username` varchar(15) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Tipologia` varchar(45) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Utente` WRITE;
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;

INSERT INTO `Utente` (`Username`, `Email`, `Password`, `Nome`, `Cognome`, `Tipologia`)
VALUES
	('alepapa','papaleo@univaq.it','1234','Alessandro','Papapleo','Utente'),
	('andcant','cantagallo@univaq.it','1234','Andrea','Cantagallo','Admin'),
	('giulia','giulia@univaq.it','1234','Giulia','Giulia','Utente'),
	('loreand','andreoli@univaq.it','1234','Lorenzo','Andreoli','Admin'),
	('matross','rossi@univaq.it','1234','Mattia','Rossi','Moderator');

/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="NO_AUTO_VALUE_ON_ZERO" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`root`@`localhost` */ /*!50003 TRIGGER `Utente_AFTER_INSERT` AFTER INSERT ON `Utente` FOR EACH ROW BEGIN
INSERT INTO Timeline(Utente_Username,Data,Exp) VALUES(NEW.Username,CURRENT_DATE,'0');
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
