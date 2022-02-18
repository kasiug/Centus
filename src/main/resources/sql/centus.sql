-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: centus
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `budgets`
--

DROP TABLE IF EXISTS `budgets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budgets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `year` smallint DEFAULT NULL,
  `month` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userIdFk` (`userId`),
  CONSTRAINT `userIdFk` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgets`
--

LOCK TABLES `budgets` WRITE;
/*!40000 ALTER TABLE `budgets` DISABLE KEYS */;
INSERT INTO `budgets` VALUES (7,150,1,2022,1),(8,164,1,2022,2),(9,174,1,2022,3),(10,180,2,2022,1),(11,190,2,2022,2),(12,200,2,2022,3),(13,200,3,2022,1),(14,168,3,2022,2),(15,179,3,2022,3),(16,4000,4,2022,2),(19,1700,1,2022,4),(20,1200,1,2022,5),(21,1500,1,2022,6),(22,1550,1,2022,7),(23,1660,1,2022,8),(24,1870,1,2022,9),(25,1670,1,2022,10),(26,1760,1,2022,11),(27,1980,1,2022,12),(28,1000,1,2021,5),(29,1200,1,2021,6),(30,1600,1,2021,7),(31,1360,1,2021,8),(32,1270,1,2021,9),(33,1170,1,2021,10),(34,1860,1,2021,11),(35,1280,1,2021,12),(36,1100,1,2021,4);
/*!40000 ALTER TABLE `budgets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `year` smallint DEFAULT NULL,
  `month` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `expenseUserIdFk` (`userId`),
  CONSTRAINT `expenseUserIdFk` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
INSERT INTO `expenses` VALUES (10,50,1,2022,1),(11,75,1,2022,2),(12,25,1,2022,3),(13,125,2,2022,1),(14,103,2,2022,2),(15,110,3,2022,1),(16,63,3,2022,2),(17,50,2,2022,3),(18,50,3,2022,1),(19,350.7,4,2022,2),(20,0.5,4,2022,2),(21,1,4,2022,2),(22,1020,4,2022,2),(23,700,4,2022,2),(24,520,4,2022,2),(25,12,4,2022,2),(26,120,4,2022,2),(27,260,4,2022,2),(28,1300,1,2022,4),(29,1100,1,2022,5),(30,100,1,2022,6),(31,1233,1,2022,7),(32,1233,1,2022,8),(33,100,1,2022,9),(34,1100,1,2022,10),(35,1000,1,2022,11),(36,1980,1,2022,12),(37,1100,1,2021,4),(38,1400,1,2021,5),(39,2100,1,2021,6),(40,1633,1,2021,7),(41,1733,1,2021,8),(42,1800,1,2021,9),(43,1800,1,2021,10),(44,700,1,2021,11),(45,1180,1,2021,12);
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `passwordHash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Anna','Lewandowska','Anna','alewandowska@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae'),(2,'Magda','Gessler','Magda','mgessler@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae'),(3,'Rachel','Zane','Rachel','rzane@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae'),(4,'test','test','test','test','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 16:50:21
