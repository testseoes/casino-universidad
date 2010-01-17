-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.42-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema casino
--

CREATE DATABASE IF NOT EXISTS casino;
USE casino;

--
-- Definition of table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
CREATE TABLE `jugadores` (
  `Login` varchar(20) NOT NULL,
  `Pass` varchar(8) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `InvertidoTotal` int(10) unsigned DEFAULT '0',
  `RecuperadoTotal` int(10) unsigned DEFAULT '0',
  `TipoJugadorUno` int(10) unsigned DEFAULT NULL,
  `TipoJugadorBlack` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Login`),
  KEY `FK_jugadores_1` (`TipoJugadorBlack`),
  CONSTRAINT `FK_jugadores_1` FOREIGN KEY (`TipoJugadorBlack`) REFERENCES `tipo_jugador_black` (`Id_Tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores`
--

/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` (`Login`,`Pass`,`Nombre`,`Apellido`,`InvertidoTotal`,`RecuperadoTotal`,`TipoJugadorUno`,`TipoJugadorBlack`) VALUES 
 ('25252525','2222','Antonio','Garcia',5,3,1,2),
 ('25434343','3333','no','Martin',20,22,2,3),
 ('25732700','1111','Silvia','Perez',10,4,3,1);
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;


--
-- Definition of table `jugadores_partidas`
--

DROP TABLE IF EXISTS `jugadores_partidas`;
CREATE TABLE `jugadores_partidas` (
  `NPartida` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Login` varchar(20) NOT NULL,
  `Invertido` int(10) unsigned DEFAULT '0',
  `Recuperado` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`NPartida`,`Login`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores_partidas`
--

/*!40000 ALTER TABLE `jugadores_partidas` DISABLE KEYS */;
INSERT INTO `jugadores_partidas` (`NPartida`,`Login`,`Invertido`,`Recuperado`) VALUES 
 (1,'25434343',1,2),
 (2,'25434343',2,2),
 (3,'25434343',3,4),
 (4,'25434343',4,4),
 (5,'25434343',5,7),
 (6,'25252525',1,1),
 (6,'25732700',1,NULL),
 (7,'25252525',1,1),
 (7,'25732700',1,NULL),
 (8,'25252525',1,NULL),
 (8,'25732700',1,1),
 (9,'25252525',1,NULL),
 (9,'25732700',1,1),
 (10,'25252525',1,1),
 (10,'25732700',1,NULL),
 (11,'25434343',1,1),
 (11,'25732700',1,0),
 (12,'25434343',1,1),
 (12,'25732700',1,0),
 (13,'25434343',1,0),
 (13,'25732700',1,1),
 (14,'25434343',1,1),
 (14,'25732700',1,0),
 (15,'25434343',1,0),
 (15,'25732700',1,1);
/*!40000 ALTER TABLE `jugadores_partidas` ENABLE KEYS */;


--
-- Definition of table `jugadores_sesiones`
--

DROP TABLE IF EXISTS `jugadores_sesiones`;
CREATE TABLE `jugadores_sesiones` (
  `Login` varchar(20) NOT NULL,
  `NSesion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Login`,`NSesion`),
  KEY `FK_jugadores_sesiones_2` (`NSesion`),
  CONSTRAINT `FK_jugadores_sesiones_1` FOREIGN KEY (`Login`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_jugadores_sesiones_2` FOREIGN KEY (`NSesion`) REFERENCES `sesion` (`NSesion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores_sesiones`
--

/*!40000 ALTER TABLE `jugadores_sesiones` DISABLE KEYS */;
INSERT INTO `jugadores_sesiones` (`Login`,`NSesion`) VALUES 
 ('25252525',1),
 ('25434343',1),
 ('25732700',1),
 ('25434343',2),
 ('25732700',2);
/*!40000 ALTER TABLE `jugadores_sesiones` ENABLE KEYS */;


--
-- Definition of table `partidas`
--

DROP TABLE IF EXISTS `partidas`;
CREATE TABLE `partidas` (
  `NPartida` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Mesa` int(10) unsigned NOT NULL,
  `NombreJuego` varchar(10) NOT NULL,
  `HoraInicioPartida` time DEFAULT NULL,
  `HoraFinPartida` time DEFAULT NULL,
  PRIMARY KEY (`NPartida`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas`
--

/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
INSERT INTO `partidas` (`NPartida`,`Mesa`,`NombreJuego`,`HoraInicioPartida`,`HoraFinPartida`) VALUES 
 (1,1,'BlackJack','18:31:55','18:31:55'),
 (2,1,'BlackJack','18:31:55','18:31:55'),
 (3,1,'BlackJack','18:31:55','18:31:56'),
 (4,1,'BlackJack','18:31:56','18:31:56'),
 (5,1,'BlackJack','18:31:56','18:31:56'),
 (6,3,'Uno','18:32:02','18:32:02'),
 (7,3,'Uno','18:32:02','18:32:02'),
 (8,3,'Uno','18:32:02','18:32:02'),
 (9,3,'Uno','18:32:02','18:32:02'),
 (10,3,'Uno','18:32:02','18:32:02'),
 (11,1,'Uno','18:34:20','18:34:20'),
 (12,1,'Uno','18:34:20','18:34:20'),
 (13,1,'Uno','18:34:20','18:34:20'),
 (14,1,'Uno','18:34:20','18:34:20'),
 (15,1,'Uno','18:34:20','18:34:21');
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;


--
-- Definition of table `partidas_sesiones`
--

DROP TABLE IF EXISTS `partidas_sesiones`;
CREATE TABLE `partidas_sesiones` (
  `NPartida` int(10) unsigned NOT NULL,
  `NSesion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NPartida`,`NSesion`),
  KEY `FK_partidas_sesiones_1` (`NSesion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas_sesiones`
--

/*!40000 ALTER TABLE `partidas_sesiones` DISABLE KEYS */;
INSERT INTO `partidas_sesiones` (`NPartida`,`NSesion`) VALUES 
 (1,1),
 (2,1),
 (3,1),
 (4,1),
 (5,1),
 (6,1),
 (7,1),
 (8,1),
 (9,1),
 (10,1),
 (11,2),
 (12,2),
 (13,2),
 (14,2),
 (15,2);
/*!40000 ALTER TABLE `partidas_sesiones` ENABLE KEYS */;


--
-- Definition of table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
CREATE TABLE `sesion` (
  `NSesion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FechaInicioSesion` date NOT NULL,
  `FechaFinSesion` date DEFAULT NULL,
  `HoraInicioSesion` time NOT NULL,
  `HoraFinSesion` time DEFAULT NULL,
  PRIMARY KEY (`NSesion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesion`
--

/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` (`NSesion`,`FechaInicioSesion`,`FechaFinSesion`,`HoraInicioSesion`,`HoraFinSesion`) VALUES 
 (1,'2010-01-17','2010-01-17','18:31:38','18:32:02'),
 (2,'2010-01-17','2010-01-17','18:34:11','18:34:21');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;


--
-- Definition of table `tipo_jugador_black`
--

DROP TABLE IF EXISTS `tipo_jugador_black`;
CREATE TABLE `tipo_jugador_black` (
  `Id_Tipo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Doblar` bit(1) NOT NULL,
  `Separar` bit(1) NOT NULL,
  `Plantarse` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id_Tipo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_jugador_black`
--

/*!40000 ALTER TABLE `tipo_jugador_black` DISABLE KEYS */;
INSERT INTO `tipo_jugador_black` (`Id_Tipo`,`Doblar`,`Separar`,`Plantarse`) VALUES 
 (1,0x00,0x00,6),
 (2,0x00,0x00,5),
 (3,0x00,0x00,16);
/*!40000 ALTER TABLE `tipo_jugador_black` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
