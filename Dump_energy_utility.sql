-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: energy_utility
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `consumption`
--

DROP TABLE IF EXISTS `consumption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumption` (
  `consumption_id` bigint NOT NULL AUTO_INCREMENT,
  `energy_consumption` float DEFAULT NULL,
  `metering_device_id` bigint DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`consumption_id`),
  KEY `FK6x29fsktg6gjdita82kitcwbk` (`metering_device_id`),
  CONSTRAINT `FK6x29fsktg6gjdita82kitcwbk` FOREIGN KEY (`metering_device_id`) REFERENCES `device` (`metering_device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumption`
--

LOCK TABLES `consumption` WRITE;
/*!40000 ALTER TABLE `consumption` DISABLE KEYS */;
INSERT INTO `consumption` VALUES (2,17.3,1,'2022-10-23','10:00:00'),(3,15.89,1,'2022-10-23','11:00:00'),(4,20.5,1,'2022-10-23','12:00:00'),(5,19.8,2,'2022-10-23','11:00:00'),(6,23.67,1,'2022-10-23','09:00:00'),(7,28.1,1,'2022-10-23','07:00:00'),(8,14.5,1,'2022-10-23','08:00:00'),(9,7.5,13,'2022-11-03','05:00:00'),(10,7.6,13,'2022-11-03','06:00:00'),(11,7.7,13,'2022-11-03','07:00:00'),(12,7.8,13,'2022-11-03','04:00:00'),(13,15,13,'2022-11-03','20:00:00'),(14,20.1,13,'2022-11-03','21:00:00'),(15,23.3,13,'2022-11-03','22:00:00'),(16,19,13,'2022-11-03','23:00:00'),(17,6.5,13,'2022-11-03','00:00:00'),(18,6.6,13,'2022-11-03','01:00:00'),(19,6.7,13,'2022-11-03','02:00:00'),(20,7,13,'2022-11-03','03:00:00'),(21,15,13,'2022-11-03','08:00:00'),(22,15.5,13,'2022-11-03','09:00:00'),(23,16,13,'2022-11-03','10:00:00'),(24,16.5,13,'2022-11-03','11:00:00'),(25,17,13,'2022-11-03','12:00:00'),(26,17.8,13,'2022-11-03','13:00:00'),(27,18.3,13,'2022-11-03','14:00:00'),(28,18,13,'2022-11-03','15:00:00'),(29,19.7,13,'2022-11-03','16:00:00'),(30,24,13,'2022-11-03','17:00:00'),(31,22,13,'2022-11-03','18:00:00'),(32,21,13,'2022-11-03','19:00:00');
/*!40000 ALTER TABLE `consumption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `metering_device_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_hourly_consumption` float DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`metering_device_id`),
  KEY `FKk92m2qj36vn62ctp5pgbt4982` (`user_id`),
  CONSTRAINT `FKk92m2qj36vn62ctp5pgbt4982` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'Str. Mehedinti 76, Ap. 12','Wireless energy meter',20.67,4),(2,'Str. Mehedinti 77, Ap. 13','Wireless Energy Meter',26.3,4),(3,'Str. Parang 76, Ap. 15','Wireless energy meter',24.5,5),(6,'Str. Parang 76, Ap. 15','Wireless energy meter',24.5,5),(7,'Str. Parang 76, Ap. 16','Wireless energy meter',25.5,5),(8,'Str. Ciresilor Nr. 22','Wireless energy meter',25.5,10),(9,'Str. Ciresilor Nr. 23','Wireless energy meter',30,5),(12,'Str. Mehedinti, Nr 88','Wireless Metering Device',23.9,5),(13,'Str. Mehedinti 76, Ap. 12','Wireless energy meter',20.67,4);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` int NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'$2a$10$/lSsML3reLuph1/nNE/EVu0WMg/CaKDtO43/sS5zhjzW7fpZzxTVK',0,'ariana1234'),(5,'$2a$10$f4cWPMG1avTnX/1H6v70ZuPW/ISEzU.HhP.UoRstzKPuasrDH7B7O',0,'client1'),(6,'$2a$10$5zvrYWWaYj6zRRxuX1NNdOiXEirxctthmz1KDFzChC52rYx0Q3s0G',0,'client2'),(8,'$2a$10$noQZb7QiamBs1olIXFzWGuBaA3lH8KqCP8iwx9VMxmhjJRlD54/jC',1,'admin1'),(9,'$2a$10$tkqlUcAJscjS4Bw6LPgd4e2jMhy7EV2kK109Lb3hkt24SWBSs0mnO',0,'client3'),(10,'$2a$10$AaOSH9NX8pNsHleVMVuvguOz9QFtBB5IHawYS67BogG85IbM/MZYy',1,'ariana'),(21,'$2a$10$BVInHvZkFBnelMwJTPZHwexh9Ily4GUaUrBm3XTA5Z.5CI4f/HR46',0,'ariana1'),(22,'$2a$10$tsDjJ04a9VyByo.48WOKV.cRpRkLJaorLEp9Z3BzaJeWDiz0FOU/W',1,'admin2'),(23,'$2a$10$vcoJBSXIMRrFoZ1uBP4Fae8aD75Wyj2R4hRi4LccuV8TcgqEUYQQy',1,'admin3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'energy_utility'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-04 17:41:00
