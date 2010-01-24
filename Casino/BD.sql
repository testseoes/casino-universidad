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
  `TipoJugadorUno` int(10) NOT NULL,
  `TipoJugadorBlack` int(10) NOT NULL,
  `PlantarseBlack` int(10) NOT NULL,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores`
--

/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` (`Login`,`Pass`,`Nombre`,`Apellido`,`InvertidoTotal`,`RecuperadoTotal`,`TipoJugadorUno`,`TipoJugadorBlack`,`PlantarseBlack`) VALUES 
 ('25000000N','0000','Romualdo','Lopez',5,2,1,1,15),
 ('25000001J','1111','Dolores','Fuertes',3,0,2,3,16),
 ('25000002Z','2222','Bart','Simpson',4,5,3,2,14),
 ('25000003S','3333','Domingo','Diaz',2,0,3,4,17),
 ('25000004Q','4444','Maria','Fontaneda',6,6,3,1,19),
 ('25000005V','6666','Lord','Voldemort',1,0,2,3,18),
 ('25000006H','7777','Pepito','DeLomo',1,2,3,1,19);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores_partidas`
--

/*!40000 ALTER TABLE `jugadores_partidas` DISABLE KEYS */;
INSERT INTO `jugadores_partidas` (`NPartida`,`Login`,`Invertido`,`Recuperado`) VALUES 
 (1,'25000000N',1,0),
 (2,'25000001J',1,0),
 (2,'25000002Z',1,2),
 (3,'25000003S',1,0),
 (3,'25000004Q',2,4),
 (4,'25000000N',1,0),
 (4,'25000001J',1,0),
 (4,'25000002Z',1,1),
 (5,'25000001J',1,0),
 (5,'25000003S',1,0),
 (6,'25000000N',1,2),
 (6,'25000004Q',1,0),
 (7,'25000000N',1,0),
 (7,'25000004Q',1,0),
 (8,'25000002Z',1,2),
 (9,'25000000N',1,0),
 (9,'25000004Q',2,2),
 (10,'25000002Z',1,0),
 (11,'25000005V',1,0),
 (11,'25000006H',1,2);
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
 ('25000000N',1),
 ('25000001J',1),
 ('25000002Z',1),
 ('25000003S',1),
 ('25000004Q',1),
 ('25000000N',2),
 ('25000001J',2),
 ('25000002Z',2),
 ('25000000N',3),
 ('25000001J',3),
 ('25000003S',3),
 ('25000004Q',3),
 ('25000000N',4),
 ('25000001J',4),
 ('25000002Z',4),
 ('25000003S',4),
 ('25000004Q',4),
 ('25000005V',5),
 ('25000006H',5);
/*!40000 ALTER TABLE `jugadores_sesiones` ENABLE KEYS */;


--
-- Definition of table `partidas`
--

DROP TABLE IF EXISTS `partidas`;
CREATE TABLE `partidas` (
  `NPartida` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Mesa` int(10) unsigned NOT NULL,
  `NombreJuego` varchar(10) NOT NULL,
  `HoraInicioPartida` time NOT NULL,
  `HoraFinPartida` time DEFAULT NULL,
  PRIMARY KEY (`NPartida`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas`
--

/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
INSERT INTO `partidas` (`NPartida`,`Mesa`,`NombreJuego`,`HoraInicioPartida`,`HoraFinPartida`) VALUES 
 (1,1,'BlackJack','20:09:26','20:09:26'),
 (2,2,'Uno','20:09:28','20:09:28'),
 (3,3,'BlackJack','20:09:35','20:09:35'),
 (4,1,'BlackJack','20:11:21','20:11:21'),
 (5,1,'BlackJack','20:12:53','20:12:53'),
 (6,2,'Uno','20:12:54','20:12:54'),
 (7,1,'BlackJack','20:13:09','20:13:09'),
 (8,3,'BlackJack','20:13:10','20:13:10'),
 (9,1,'BlackJack','20:14:33','20:14:33'),
 (10,3,'BlackJack','20:14:35','20:14:35'),
 (11,2,'Uno','20:18:19','20:18:19');
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
 (4,2),
 (5,3),
 (6,3),
 (7,4),
 (8,4),
 (9,4),
 (10,4),
 (11,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesion`
--

/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` (`NSesion`,`FechaInicioSesion`,`FechaFinSesion`,`HoraInicioSesion`,`HoraFinSesion`) VALUES 
 (1,'2010-01-24','2010-01-24','20:08:56','20:09:35'),
 (2,'2010-01-24','2010-01-24','20:11:09','20:11:21'),
 (3,'2010-01-24','2010-01-24','20:12:43','20:12:54'),
 (4,'2010-01-24','2010-01-24','20:13:01','20:15:15'),
 (5,'2010-01-24','2010-01-24','20:15:41','20:18:43');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
