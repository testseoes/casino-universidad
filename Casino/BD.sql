﻿-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.42-community
-- SIEMPRE QUE CARGUEIS DE NUEVO LA BD CON: QUERYBROWSER->OPEN SCRIPT
-- BORRAD LA ANTERIOR BD CON: BOTON DERECHO SOBRE LA BD->DROP SCHEMA

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
  PRIMARY KEY (`NPartida`,`Login`) USING BTREE,
  KEY `FK_jugadores_partidas_1` (`Login`),
  CONSTRAINT `FK_jugadores_partidas_1` FOREIGN KEY (`Login`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_jugadores_partidas_2` FOREIGN KEY (`NPartida`) REFERENCES `partidas` (`NPartida`)
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
  KEY `FK_jugadores_sesiones_2` (`NSesion`),
  CONSTRAINT `FK_jugadores_sesiones_1` FOREIGN KEY (`Login`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_jugadores_sesiones_2` FOREIGN KEY (`NSesion`) REFERENCES `sesion` (`NSesion`)
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
-- Definition of table `partidas_sesiones`
--

DROP TABLE IF EXISTS `partidas_sesiones`;
CREATE TABLE `partidas_sesiones` (
  `NPartida` int(10) unsigned NOT NULL,
  `NSesion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NPartida`,`NSesion`),
  KEY `FK_partidas_sesiones_1` (`NSesion`),
  CONSTRAINT `FK_partidas_sesiones_1` FOREIGN KEY (`NSesion`) REFERENCES `sesion` (`NSesion`),
  CONSTRAINT `FK_partidas_sesiones_2` FOREIGN KEY (`NPartida`) REFERENCES `partidas` (`NPartida`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas_sesiones`
--

/*!40000 ALTER TABLE `partidas_sesiones` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesion`
--

/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_jugador_black`
--

/*!40000 ALTER TABLE `tipo_jugador_black` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_jugador_black` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;