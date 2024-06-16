-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: localhost    Database: dealsplus
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.22.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `companyId` bigint NOT NULL,
  `companyInfo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'company-100','c100'),(2,'company-101','c101'),(3,'company-102','c102'),(4,'company-200','c200'),(5,'company-201','c201'),(6,'company-202','c202'),(7,'company-300','c300'),(8,'company-301','c301'),(9,'company-302','c302');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_SEQ`
--

DROP TABLE IF EXISTS `company_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_SEQ`
--

LOCK TABLES `company_SEQ` WRITE;
/*!40000 ALTER TABLE `company_SEQ` DISABLE KEYS */;
INSERT INTO `company_SEQ` VALUES (101);
/*!40000 ALTER TABLE `company_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refreshtoken`
--

DROP TABLE IF EXISTS `refreshtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refreshtoken` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refreshtoken`
--

LOCK TABLES `refreshtoken` WRITE;
/*!40000 ALTER TABLE `refreshtoken` DISABLE KEYS */;
INSERT INTO `refreshtoken` VALUES (1,'2024-06-16 06:13:26.197027','bc33e7f7-14f4-4f35-b95c-bc35a59af0e9'),(2,'2024-06-16 06:16:54.975967','60a98efd-bb18-4d6e-85d0-dc92f70a2138'),(3,'2024-06-16 06:27:49.546694','3a23d557-9d51-4dc4-8c91-da8f236b7882'),(4,'2024-06-16 06:44:19.213612','49c24536-abbb-49df-a70c-67af5cdffe5c'),(5,'2024-06-16 06:48:02.709327','d1429920-9d86-4956-a632-5bb073f78c1c'),(6,'2024-06-16 06:48:30.052339','92a74f5c-dbfa-4ca8-8c57-92705e780859'),(7,'2024-06-16 07:12:27.344186','26e29ec1-1db1-4923-ae2f-66fa1e599f84'),(8,'2024-06-16 09:46:42.682692','a718c962-89c0-4f0e-829d-b19b0cdd326f'),(9,'2024-06-16 10:34:59.609872','235314b3-03b0-4023-9f39-a517bfad9bc7'),(10,'2024-06-16 10:46:07.613980','7b096799-c323-4da1-87e3-aa058cb1c9c3'),(11,'2024-06-16 10:48:33.565681','af3913c6-5c3d-4f96-b24a-b5c6fc87b6d1'),(12,'2024-06-16 11:50:13.275893','86582c2e-a813-4e14-9df6-29334a78a8c0'),(13,'2024-06-16 12:57:49.268812','bfdbdc51-617f-4ab7-b092-962b2d7609de');
/*!40000 ALTER TABLE `refreshtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `roleId` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `roleEntity` tinyint DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'STRUCTURE_READ_AUTHORITY',NULL),(3,'STRUCTURE_EDIT_AUTHORITY',NULL),(4,'STRUCTURE_ADMIN_AUTHORITY',NULL),(5,'ADMIN',NULL),(6,'USER',NULL),(15,'STRUCTURE_DELETE_AUTHORITY',NULL),(143,'COMPANY_READ_AUTHORITY',NULL),(144,'COMPANY_EDIT_AUTHORITY',NULL),(145,'COMPANY_ADMIN_AUTHORITY',NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `structure`
--

DROP TABLE IF EXISTS `structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `structure` (
  `structureId` bigint NOT NULL,
  `structureName` varchar(255) DEFAULT NULL,
  `structureInfo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`structureId`),
  UNIQUE KEY `UK_s1x6y7f3ol7nxuoafmtb0ly46` (`structureName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `structure`
--

LOCK TABLES `structure` WRITE;
/*!40000 ALTER TABLE `structure` DISABLE KEYS */;
INSERT INTO `structure` VALUES (1,'struct:1','info1'),(2,'struct:2','info2'),(3,'struct:3','info3');
/*!40000 ALTER TABLE `structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `structure_SEQ`
--

DROP TABLE IF EXISTS `structure_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `structure_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `structure_SEQ`
--

LOCK TABLES `structure_SEQ` WRITE;
/*!40000 ALTER TABLE `structure_SEQ` DISABLE KEYS */;
INSERT INTO `structure_SEQ` VALUES (101);
/*!40000 ALTER TABLE `structure_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `structure_company`
--

DROP TABLE IF EXISTS `structure_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `structure_company` (
  `structureId` bigint NOT NULL,
  `companyId` bigint NOT NULL,
  KEY `FK4mojjy6bul2stx36gxn2n87rq` (`companyId`),
  KEY `FKok45l7ggonnscmeudp9xnkj4y` (`structureId`),
  CONSTRAINT `FK4mojjy6bul2stx36gxn2n87rq` FOREIGN KEY (`companyId`) REFERENCES `company` (`companyId`),
  CONSTRAINT `FKok45l7ggonnscmeudp9xnkj4y` FOREIGN KEY (`structureId`) REFERENCES `structure` (`structureId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `structure_company`
--

LOCK TABLES `structure_company` WRITE;
/*!40000 ALTER TABLE `structure_company` DISABLE KEYS */;
INSERT INTO `structure_company` VALUES (1,1);
/*!40000 ALTER TABLE `structure_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL,
  `expiryDate` datetime(6) DEFAULT NULL,
  `now` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_userId` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gltptba06fmf7wrdj5yuj1vql` (`user_userId`),
  CONSTRAINT `FK8cow168gjixn3mvkbh6mwhrbt` FOREIGN KEY (`user_userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_SEQ`
--

DROP TABLE IF EXISTS `token_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_SEQ`
--

LOCK TABLES `token_SEQ` WRITE;
/*!40000 ALTER TABLE `token_SEQ` DISABLE KEYS */;
INSERT INTO `token_SEQ` VALUES (1);
/*!40000 ALTER TABLE `token_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_SEQ`
--

DROP TABLE IF EXISTS `user_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_SEQ`
--

LOCK TABLES `user_SEQ` WRITE;
/*!40000 ALTER TABLE `user_SEQ` DISABLE KEYS */;
INSERT INTO `user_SEQ` VALUES (1);
/*!40000 ALTER TABLE `user_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` bigint NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'2024-06-16 11:39:24.000000','admin@example.com',_binary '','$2a$10$VdOP5tsUE4COnt1bEvexM.RkMBxkS/HdHgnRh9LEJG8XzUeX.izPC','admin'),(52,'2024-06-16 09:45:10.557988','structure_admin@mail.com',_binary '','$2a$10$Qc2RnpbS/H8LocEov87Vz.vwUQUwtZWUnQ1ZycotGqlhcXymYsdza','structure_admin'),(102,'2024-06-16 10:34:46.185931','structure_read@mail.com',_binary '','$2a$10$RVG2Hehi/we8byuTxv1AneSOWmKlBnzdJmGFaw.ABXqwiAtvWFQgq','structure_read'),(103,'2024-06-16 10:45:58.318381','structure_edit@mail.com',_binary '','$2a$10$7jJkBzswnnCbnIjzDEOZq.ypBno4NMkqx8reU7PK1LYWLxNlDXs26','structure_edit'),(152,'2024-06-16 11:50:02.579203','company_admin@mail.com',_binary '','$2a$10$ldyH/3pYfKWoZdhDE6UO5ehVCdoeJCGn1FjXqJ3SWSpEzyutOJ9Fa','company_admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_SEQ`
--

DROP TABLE IF EXISTS `users_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_SEQ`
--

LOCK TABLES `users_SEQ` WRITE;
/*!40000 ALTER TABLE `users_SEQ` DISABLE KEYS */;
INSERT INTO `users_SEQ` VALUES (251);
/*!40000 ALTER TABLE `users_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `userId` bigint NOT NULL,
  `roleId` bigint NOT NULL,
  UNIQUE KEY `unique_user_role_map` (`userId`,`roleId`),
  KEY `FK813pythy5xd9i3e8chcj0i9wg` (`roleId`),
  CONSTRAINT `FK813pythy5xd9i3e8chcj0i9wg` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`),
  CONSTRAINT `FKcm4j55mcneyml38dlsojlbloe` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (102,1),(103,3),(52,4),(0,5),(102,6),(103,6),(152,6),(152,145);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-16 18:34:44
