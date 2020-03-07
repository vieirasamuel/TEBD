CREATE DATABASE  IF NOT EXISTS `eventos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `eventos`;
-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: eventos
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artigo`
--

DROP TABLE IF EXISTS `artigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artigo` (
  `idartigo` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `resumo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idartigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `idautor` int NOT NULL,
  `fk_participante` int NOT NULL,
  `fk_artigo` int NOT NULL,
  PRIMARY KEY (`idautor`),
  KEY `fk_autor_2_idx` (`fk_artigo`),
  KEY `fk_autor_1_idx` (`fk_participante`),
  CONSTRAINT `fk_autor_1` FOREIGN KEY (`fk_participante`) REFERENCES `participante` (`idparticipante`),
  CONSTRAINT `fk_autor_2` FOREIGN KEY (`fk_artigo`) REFERENCES `artigo` (`idartigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `idavaliacao` int NOT NULL AUTO_INCREMENT,
  `fk_participante` int NOT NULL,
  `fk_artigo` int NOT NULL,
  `nota` int DEFAULT NULL,
  `comentario` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idavaliacao`),
  KEY `fk_avaliacao_1_idx` (`fk_participante`),
  KEY `fk_avaliacao_2_idx` (`fk_artigo`),
  CONSTRAINT `fk_avaliacao_1` FOREIGN KEY (`fk_participante`) REFERENCES `participante` (`idparticipante`),
  CONSTRAINT `fk_avaliacao_2` FOREIGN KEY (`fk_artigo`) REFERENCES `artigo` (`idartigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `congresso`
--

DROP TABLE IF EXISTS `congresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `congresso` (
  `idcongresso` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idcongresso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participante` (
  `idparticipante` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `empresa` varchar(45) DEFAULT NULL,
  `numero_cartao` varchar(45) DEFAULT NULL,
  `vencimento_cartao` date DEFAULT NULL,
  `bandeira_cartao` varchar(45) DEFAULT NULL,
  `avaliador` tinyint DEFAULT '0',
  `fk_congresso` int NOT NULL,
  PRIMARY KEY (`idparticipante`),
  KEY `fk_participante_1_idx` (`fk_congresso`),
  CONSTRAINT `fk_participante_1` FOREIGN KEY (`fk_congresso`) REFERENCES `congresso` (`idcongresso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-06 22:15:39
