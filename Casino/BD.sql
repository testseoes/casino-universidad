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
 ('25000000K','0000','James','Bond',15,16,3,4,16),
 ('25000001G','1111','Yoni','Melavo',19,4,2,1,16);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores_partidas`
--

/*!40000 ALTER TABLE `jugadores_partidas` DISABLE KEYS */;
INSERT INTO `jugadores_partidas` (`NPartida`,`Login`,`Invertido`,`Recuperado`) VALUES 
 (1,'25000000K',1,1),
 (1,'25000001G',1,0),
 (2,'25000000K',2,3),
 (2,'25000001G',3,0),
 (3,'25000000K',3,4),
 (3,'25000001G',4,0),
 (4,'25000000K',4,4),
 (4,'25000001G',5,2),
 (5,'25000000K',5,4),
 (5,'25000001G',6,2);
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
 ('25000000K',1),
 ('25000001G',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas`
--

/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
INSERT INTO `partidas` (`NPartida`,`Mesa`,`NombreJuego`,`HoraInicioPartida`,`HoraFinPartida`) VALUES 
 (1,1,'BlackJack','15:20:55','15:20:55'),
 (2,1,'BlackJack','15:20:55','15:20:55'),
 (3,1,'BlackJack','15:20:55','15:20:55'),
 (4,1,'BlackJack','15:20:55','15:20:55'),
 (5,1,'BlackJack','15:20:55','15:20:55');
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
 (5,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesion`
--

/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` (`NSesion`,`FechaInicioSesion`,`FechaFinSesion`,`HoraInicioSesion`,`HoraFinSesion`) VALUES 
 (1,'2010-01-19','2010-01-19','15:20:47','15:20:55');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
