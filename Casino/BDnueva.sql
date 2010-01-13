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
-- Create schema kasino
--

CREATE DATABASE IF NOT EXISTS kasino;
USE kasino;

--
-- Definition of table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
CREATE TABLE `jugadores` (
  `Login` varchar(20) NOT NULL,
  `Pass` varchar(8) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `InvertidoTotal` int(10) unsigned DEFAULT NULL,
  `RecuperadoTotal` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores`
--

/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` (`Login`,`Pass`,`Nombre`,`Apellido`,`InvertidoTotal`,`RecuperadoTotal`) VALUES 
 ('23456745K','3333','Manuela','Mayorga',NULL,NULL),
 ('25698745L','1111','Horacio','Perez',NULL,NULL),
 ('25732700R','4444','Raul','Gutierrez',NULL,NULL),
 ('X3135830X','2222','Jose','Gonzalez',NULL,NULL);
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;


--
-- Definition of table `jugadores_partidas`
--

DROP TABLE IF EXISTS `jugadores_partidas`;
CREATE TABLE `jugadores_partidas` (
  `NPartida` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Login` varchar(20) NOT NULL,
  `Invertido` int(10) unsigned NOT NULL,
  `Recuperado` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NPartida`),
  KEY `FK_jugadores_partidas_1` (`Login`),
  CONSTRAINT `FK_jugadores_partidas_2` FOREIGN KEY (`NPartida`) REFERENCES `partidas` (`NPartida`),
  CONSTRAINT `FK_jugadores_partidas_1` FOREIGN KEY (`Login`) REFERENCES `jugadores` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores_partidas`
--

/*!40000 ALTER TABLE `jugadores_partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugadores_partidas` ENABLE KEYS */;


--
-- Definition of table `jugadores_sesiones`
--

DROP TABLE IF EXISTS `jugadores_sesiones`;
CREATE TABLE `jugadores_sesiones` (
  `Login` varchar(20) NOT NULL,
  `NSesion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Login`,`NSesion`),
  KEY `FK_jugadores_sesiones_1` (`NSesion`),
  CONSTRAINT `FK_jugadores_sesiones_2` FOREIGN KEY (`Login`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_jugadores_sesiones_1` FOREIGN KEY (`NSesion`) REFERENCES `sesion` (`NSesion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores_sesiones`
--

/*!40000 ALTER TABLE `jugadores_sesiones` DISABLE KEYS */;
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
  `HoraFinPartida` time NOT NULL,
  PRIMARY KEY (`NPartida`),
  CONSTRAINT `FK_partidas_1` FOREIGN KEY (`NPartida`) REFERENCES `jugadores_partidas` (`NPartida`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas`
--

/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;


--
-- Definition of table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
CREATE TABLE `sesion` (
  `NSesion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FechaInicioSesion` datetime NOT NULL,
  `FechaFinSesion` datetime NOT NULL,
  `HoraInicioSesion` time NOT NULL,
  `HoraFinSesion` time NOT NULL,
  `PartidaInicialSesion` int(10) unsigned NOT NULL,
  `PartidaFinSesion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NSesion`),
  KEY `FK_sesion_1` (`PartidaInicialSesion`),
  KEY `FK_sesion_2` (`PartidaFinSesion`),
  CONSTRAINT `FK_sesion_2` FOREIGN KEY (`PartidaFinSesion`) REFERENCES `partidas` (`NPartida`),
  CONSTRAINT `FK_sesion_1` FOREIGN KEY (`PartidaInicialSesion`) REFERENCES `partidas` (`NPartida`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesion`
--

/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
