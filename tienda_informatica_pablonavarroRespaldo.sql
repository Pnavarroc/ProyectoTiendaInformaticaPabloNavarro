-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: tienda_informatica_pablonavarro
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_persona` int NOT NULL,
  `contraseña` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (4,'1234'),(5,'1234'),(6,'1234'),(7,'1234');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `id_compra` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `id_empleado` int NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_persona`),
  CONSTRAINT `compra_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene`
--

DROP TABLE IF EXISTS `contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene` (
  `id_compra` int NOT NULL,
  `id_producto` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_compra`,`id_producto`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `contiene_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id_compra`),
  CONSTRAINT `contiene_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene`
--

LOCK TABLES `contiene` WRITE;
/*!40000 ALTER TABLE `contiene` DISABLE KEYS */;
/*!40000 ALTER TABLE `contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `id_persona` int NOT NULL,
  `id_jefe` int DEFAULT NULL,
  `contraseña` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,NULL,'1234'),(2,NULL,'abcd'),(3,NULL,'1234');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Laura Pérez','laura@tienda.com','111222333','Calle Sol 10'),(2,'Carlos Gómez','carlos@tienda.com','444555666','Av. Luna 45'),(3,'María Ruiz','maria@tienda.com','777888999','Calle Estrella 12'),(4,'Pablo','pnavarroc@iesch.org','653843495','Avenida Sanz Gadea'),(5,'Saul','saul@gmail.com','678231680','Calle los Ojos 5'),(6,'Javier','javier@gmail.com','657889909','jsahdsad'),(7,'afdsg','adfgdfgad','gadfgf','adfadgadfg');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `marca` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Teclado Mecánico RGB','Corsair',89.99),(2,'Ratón Inalámbrico','Logitech',49.99),(3,'Monitor 27\" 144Hz','ASUS',249.99),(4,'Mesa Gaming LED','Trust',199.99),(5,'Silla Ergonómica Gaming','DXRacer',299.99),(6,'Teclado Office','Microsoft',29.99),(7,'Ratón Básico','HP',14.99),(8,'Monitor 24\" IPS','Samsung',169.99),(9,'Silla Básica','IKEA',89.99),(10,'Mesa de Escritorio','IKEA',129.99),(11,'Teclado Gaming RGB 101','Razer',119.99),(12,'Teclado Gaming RGB 102','Corsair',109.99),(13,'Teclado Gaming Mecánico 103','HyperX',99.99),(14,'Teclado Tenkeyless 104','SteelSeries',89.99),(15,'Teclado Gaming Silencioso 105','MSI',79.99),(16,'Ratón Gaming Pro 201','Logitech',59.99),(17,'Ratón con Peso Ajustable 202','Corsair',69.99),(18,'Ratón Ultra Ligero 203','Razer',79.99),(19,'Ratón RGB 204','Redragon',44.99),(20,'Ratón Ergonómico 205','Trust Gaming',49.99),(21,'Monitor Gaming 24\" 144Hz','ASUS',229.99),(22,'Monitor Curvo 27\" 165Hz','MSI',319.99),(23,'Monitor UltraWide 34\"','LG',479.99),(24,'Monitor Gaming 27\" IPS','Samsung',259.99),(25,'Monitor TN 144Hz 206','AOC',199.99),(26,'Silla Gaming Ergonómica 301','DXRacer',329.99),(27,'Silla Racing Estilo F1 302','Newskill',279.99),(28,'Silla Gaming Básica 303','IKEA',149.99),(29,'Silla con Reposapiés 304','Trust',199.99),(30,'Silla Premium Gaming 305','Corsair',349.99),(31,'Mesa Gaming LED 401','Trust Gaming',229.99),(32,'Mesa Grande RGB 402','Newskill',249.99),(33,'Mesa Compacta 403','Mars Gaming',159.99),(34,'Mesa de Esquina Gaming 404','IKEA',189.99),(35,'Mesa con Altura Ajustable 405','Cougar',299.99),(36,'Auriculares 7.1 Surround 501','HyperX',119.99),(37,'Headset RGB 502','Razer',139.99),(38,'Auriculares con Micrófono 503','Logitech',89.99),(39,'Auriculares Livianos 504','SteelSeries',99.99),(40,'Headset USB Pro 505','Corsair',109.99);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-20 17:19:58
