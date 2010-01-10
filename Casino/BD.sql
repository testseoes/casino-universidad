-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	6.0.10-alpha-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema `casino uni`
--

CREATE DATABASE IF NOT EXISTS `casino`;
USE `casino`;

--
-- Definition of table `casino`
--

DROP TABLE IF EXISTS `casino`;
CREATE TABLE `casino` (
  `NTotalJugadores` int(10) unsigned NOT NULL,
  `NJugadoresUnoActuales` int(10) unsigned NOT NULL,
  `NJugadoresUnoTotales` int(10) unsigned NOT NULL,
  `NJugadoresBlackActuales` int(10) unsigned NOT NULL,
  `NJugadoresBlackTotales` int(10) unsigned NOT NULL,
  `NMesasTotales` int(10) unsigned NOT NULL,
  `NPartidas Jugadas` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NTotalJugadores`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `casino`
--

/*!40000 ALTER TABLE `casino` DISABLE KEYS */;
/*!40000 ALTER TABLE `casino` ENABLE KEYS */;


--
-- Definition of table `estadisticas sesion`
--

DROP TABLE IF EXISTS `estadisticas_sesion`;
CREATE TABLE `estadisticas_sesion` (
  `NSesion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FechaHoraInicioSesion` datetime NOT NULL,
  `FechaHoraFinSesion` datetime NOT NULL,
  `PartidaInicialSesion` int(10) unsigned NOT NULL,
  `PartidaFinalSesion` int(10) unsigned NOT NULL,
  `Login1` char(10) NOT NULL,
  `Login2` char(10) DEFAULT NULL,
  `Login3` char(10) DEFAULT NULL,
  `Login4` char(10) DEFAULT NULL,
  `Login5` char(10) DEFAULT NULL,
  `Login6` char(10) DEFAULT NULL,
  `Login7` char(10) DEFAULT NULL,
  `Login8` char(10) DEFAULT NULL,
  `Login9` char(10) DEFAULT NULL,
  `Login10` char(10) DEFAULT NULL,
  `Login11` char(10) DEFAULT NULL,
  `Login12` char(10) DEFAULT NULL,
  PRIMARY KEY (`NSesion`),
  KEY `FK_estadisticas_sesion_1` (`Login1`),
  KEY `FK_estadisticas_sesion_2` (`Login2`),
  KEY `FK_estadisticas_sesion_3` (`Login3`),
  KEY `FK_estadisticas_sesion_4` (`Login4`),
  KEY `FK_estadisticas_sesion_5` (`Login5`),
  KEY `FK_estadisticas_sesion_6` (`Login6`),
  KEY `FK_estadisticas_sesion_7` (`Login7`),
  KEY `FK_estadisticas_sesion_8` (`Login8`),
  KEY `FK_estadisticas_sesion_9` (`Login9`),
  KEY `FK_estadisticas_sesion_10` (`Login10`),
  KEY `FK_estadisticas_sesion_11` (`Login11`),
  KEY `FK_estadisticas_sesion_12` (`Login12`),
  KEY `FK_estadisticas_sesion_14` (`PartidaInicialSesion`),
  KEY `FK_estadisticas_sesion_15` (`PartidaFinalSesion`),
  CONSTRAINT `FK_estadisticas_sesion_1` FOREIGN KEY (`Login1`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_10` FOREIGN KEY (`Login10`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_11` FOREIGN KEY (`Login11`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_12` FOREIGN KEY (`Login12`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_14` FOREIGN KEY (`PartidaInicialSesion`) REFERENCES `partidas` (`NPartida`),
  CONSTRAINT `FK_estadisticas_sesion_15` FOREIGN KEY (`PartidaFinalSesion`) REFERENCES `partidas` (`NPartida`),
  CONSTRAINT `FK_estadisticas_sesion_2` FOREIGN KEY (`Login2`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_3` FOREIGN KEY (`Login3`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_4` FOREIGN KEY (`Login4`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_5` FOREIGN KEY (`Login5`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_6` FOREIGN KEY (`Login6`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_7` FOREIGN KEY (`Login7`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_8` FOREIGN KEY (`Login8`) REFERENCES `jugadores` (`Login`),
  CONSTRAINT `FK_estadisticas_sesion_9` FOREIGN KEY (`Login9`) REFERENCES `jugadores` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estadisticas sesion`
--

/*!40000 ALTER TABLE `estadisticas_sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadisticas_sesion` ENABLE KEYS */;


--
-- Definition of table `juego`
--

DROP TABLE IF EXISTS `juego`;
CREATE TABLE `juego` (
  `NombreJuego` char(10) NOT NULL,
  `NJugadores Min` int(10) unsigned NOT NULL,
  `NJugadores Max` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NombreJuego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `juego`
--

/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
/*!40000 ALTER TABLE `juego` ENABLE KEYS */;


--
-- Definition of table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
CREATE TABLE `jugadores` (
  `Login` char(10) NOT NULL,
  `Pass` varchar(20) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `EstoyJugando` bit(1) NOT NULL,
  `PartidasGanadas Uno` int(10) unsigned NOT NULL,
  `PartidasTotales Uno` int(10) unsigned NOT NULL,
  `PuntosAcumulados Uno` int(10) unsigned NOT NULL,
  `DineroInvertidoBlack` int(10) unsigned NOT NULL,
  `DineroGanadoBlack` int(10) unsigned NOT NULL,
  `NUltimaSesion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Login`),
  KEY `FK_jugadores_1` (`NUltimaSesion`),
  CONSTRAINT `FK_jugadores_1` FOREIGN KEY (`NUltimaSesion`) REFERENCES `estadisticas_sesion` (`NSesion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugadores`
--

/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;


--
-- Definition of table `partidas`
--

DROP TABLE IF EXISTS `partidas`;
CREATE TABLE `partidas` (
  `NPartida` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NombreJuego` char(10) NOT NULL,
  `HoraInicioPartida` time NOT NULL,
  `HoraFinPartida` time NOT NULL,
  `Ganador` varchar(15) NOT NULL,
  `PuntosObtenidos` int(10) unsigned NOT NULL,
  PRIMARY KEY (`NPartida`),
  KEY `FK_partidas_1` (`NombreJuego`),
  CONSTRAINT `FK_partidas_1` FOREIGN KEY (`NombreJuego`) REFERENCES `juego` (`NombreJuego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partidas`
--

/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;