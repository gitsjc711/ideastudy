-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: idea-study
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2024-06-21 09:15:56','2024-06-21 09:15:58',0,'计算机',0),(2,'2024-06-21 15:56:57','2024-06-21 15:56:57',0,'电子信息',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `chapter_order` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (1,'2024-06-21 16:26:20','2024-06-21 16:26:20',0,1,'排序算法',23),(2,'2024-07-01 11:10:23','2024-07-01 11:10:23',0,1,'元素反应',29),(3,'2024-07-01 15:11:41','2024-07-01 15:11:41',0,1468124,'数据结构',20),(4,'2024-07-01 15:12:11','2024-07-01 15:12:11',0,1234,'数据',20);
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `reply_id` int NOT NULL,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'2024-06-24 15:45:38','2024-06-24 15:45:38',0,'iuafa',0,8,23),(2,'2024-07-02 15:37:07','2024-07-02 15:37:07',0,'不如原神',0,6,29),(4,'2024-07-02 16:04:17','2024-07-02 16:04:17',0,'差不多得了',2,6,29),(5,'2024-07-02 16:04:39','2024-07-02 16:04:39',0,'你好',0,6,29),(6,'2024-07-02 16:08:34','2024-07-02 16:08:34',0,'唉',2,6,29),(7,'2024-07-02 16:10:53','2024-07-02 16:10:53',0,'1',0,6,29),(8,'2024-07-02 16:10:58','2024-07-02 16:10:58',0,'+3',2,6,29),(9,'2024-07-02 16:11:07','2024-07-02 16:11:07',0,'+3',0,6,29);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `course_logo` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `teacher_id` int NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'2024-06-21 09:48:00','2024-06-21 10:09:51',0,'CSAPP','好课','G:\\resource\\image\\cover.jpg',0,6,1),(20,'2024-06-21 15:45:56','2024-06-21 15:45:56',2,'datastructure','数据结构是计算机科学的重要书籍','G:\\resource\\image\\cover.jpg',92.13,8,2),(23,'2024-06-21 16:11:43','2024-06-21 16:13:10',0,'zhanghao','深入了解计算机系统','G:\\resource\\image\\cover.jpg',21.12,8,1),(24,'2024-06-25 14:04:01','2024-06-25 14:04:01',2,'EnCode','好课','G:\\resource\\image\\cover.jpg',0,6,1),(25,'2024-06-25 14:04:56','2024-06-25 14:07:49',0,'Math','1','G:\\resource\\image\\cover.jpg',0,6,1),(29,'2024-06-29 10:25:40','2024-06-29 10:25:40',0,'原神','1','G:\\resource\\image\\6c9f10ceb7b23c556011b62aaee5d0a9.png',1,6,1),(30,'2024-06-29 11:15:58','2024-06-29 11:15:58',0,'数据','无','G:\\resource\\image\\Screenshot 2024-06-14 145350.png',100,8,2);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homework` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `course_id` int NOT NULL,
  `chapter_order` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
INSERT INTO `homework` VALUES (1,'2024-06-24 15:51:25','2024-06-24 15:51:25',0,'zhangsan','zhangsan',23,1),(2,'2024-07-01 14:45:15','2024-07-01 14:45:15',0,'熟悉元素反应','草+雷=？',29,1),(3,'2024-07-01 15:13:38','2024-07-01 15:13:38',0,'数据','1234',20,1234),(4,'2024-07-01 15:13:39','2024-07-01 15:13:39',0,'数据','1234',20,1234);
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework_student`
--

DROP TABLE IF EXISTS `homework_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homework_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `user_id` int NOT NULL,
  `homework_id` int NOT NULL,
  `homework_url` varchar(255) DEFAULT NULL,
  `homework_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework_student`
--

LOCK TABLES `homework_student` WRITE;
/*!40000 ALTER TABLE `homework_student` DISABLE KEYS */;
INSERT INTO `homework_student` VALUES (9,'2024-07-04 15:52:26','2024-07-04 15:52:26',0,6,2,'G:\\resource\\file\\作业.pdf','application/pdf');
/*!40000 ALTER TABLE `homework_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `course_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'2024-06-21 16:50:57','2024-06-21 16:50:57',0,23,8,'注意','注意通知'),(2,'2024-07-01 14:25:27','2024-07-01 14:25:27',0,29,6,'学习元素反应','学习元素反应资料1'),(3,'2024-07-01 15:13:20','2024-07-01 15:13:20',0,20,8,'数据','1234');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `order_no` varchar(64) NOT NULL,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `actual_price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (20,'2024-06-27 15:53:07','2024-06-27 15:53:34',0,'627689',20,11,92.13),(23,'2024-06-27 16:08:18','2024-06-27 16:08:18',0,'60422',23,11,21.12),(26,'2024-06-28 08:29:42','2024-06-28 08:29:42',0,'21224',1,11,0),(27,'2024-06-28 08:40:55','2024-06-28 08:40:55',0,'226316',24,11,0),(33,'2024-06-28 08:49:51','2024-06-28 08:49:51',0,'883219',25,11,0),(34,'2024-06-28 08:52:20','2024-06-28 08:52:20',0,'814830',1,10,0),(35,'2024-06-28 11:13:57','2024-06-28 11:13:57',0,'300155',1,8,0),(36,'2024-06-28 11:14:01','2024-06-28 11:14:01',0,'562825',25,8,0),(37,'2024-06-28 11:14:05','2024-06-28 11:15:41',0,'749809',20,8,92.13),(41,'2024-06-28 11:18:59','2024-06-28 11:19:13',0,'325020',23,8,21.12),(42,'2024-06-28 11:23:33','2024-06-28 11:23:33',0,'787023',24,8,0),(43,'2024-06-28 11:30:38','2024-06-28 11:30:38',0,'418120',24,10,0),(45,'2024-06-28 14:09:59','2024-06-28 14:11:24',0,'645598',20,10,92.13),(47,'2024-06-28 14:13:36','2024-06-28 14:13:36',0,'603134',25,10,0),(48,'2024-06-29 10:46:35','2024-06-29 10:46:35',2,'646703',29,8,1),(49,'2024-06-29 11:22:51','2024-06-29 11:22:51',2,'456772',29,11,1),(50,'2024-06-29 14:02:54','2024-06-29 14:02:54',0,'173729',25,12,0),(51,'2024-06-29 14:02:56','2024-06-29 14:02:56',0,'245497',24,12,0),(52,'2024-06-29 14:03:01','2024-06-29 14:06:44',0,'103914',23,12,21.12),(53,'2024-06-29 15:23:41','2024-06-29 15:23:41',2,'217449',30,8,100),(54,'2024-06-29 15:45:55','2024-06-29 15:45:55',0,'379326',1,6,0),(55,'2024-06-29 16:42:37','2024-06-29 16:43:09',0,'60741',23,6,21.12),(56,'2024-07-01 08:49:51','2024-07-01 08:49:51',0,'413416',25,6,0),(57,'2024-07-01 14:47:19','2024-07-01 14:48:06',0,'656360',29,6,1),(58,'2024-07-02 10:47:58','2024-07-02 10:47:58',0,'78769',24,6,0),(59,'2024-07-03 17:07:03','2024-07-03 17:07:03',2,'417444',30,11,100);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progress`
--

DROP TABLE IF EXISTS `progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progress` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `user_id` int NOT NULL,
  `resource_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progress`
--

LOCK TABLES `progress` WRITE;
/*!40000 ALTER TABLE `progress` DISABLE KEYS */;
INSERT INTO `progress` VALUES (1,'2024-07-02 09:35:04','2024-07-02 09:35:04',0,6,9),(2,'2024-07-02 09:35:35','2024-07-02 09:35:35',0,6,11),(3,'2024-07-03 17:13:28','2024-07-03 17:13:28',0,11,10);
/*!40000 ALTER TABLE `progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `chapter_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (7,'2024-06-25 15:35:27','2024-06-25 15:35:27',0,'深入学习CSAPP','image','G:\\resource\\image\\wallhaven-1kgkw1.jpg',1),(8,'2024-06-25 15:38:30','2024-06-25 15:38:30',0,'深入学习CSAPP','image','G:\\resource\\image\\wallhaven-1kgllv.jpg',1),(9,'2024-07-01 14:04:22','2024-07-01 14:04:22',0,'元素反应1','image/jpeg','G:\\resource\\image\\wallhaven-q2mjm7.jpg',2),(10,'2024-07-01 15:12:56','2024-07-01 15:12:56',0,'数据','image/jpeg','G:\\resource\\image\\a496008c58815f0e1677ba24013f19bb.jpg',4),(11,'2024-07-01 15:22:10','2024-07-01 15:22:10',0,'学java救不了原神','application/pdf','G:\\resource\\file\\Java开发手册（嵩山版）.pdf',2),(12,'2024-07-02 16:36:32','2024-07-02 16:36:32',0,'元素反应教学视频','video/mp4','G:\\resource\\video\\4769fd26be468b96d9f5c3265222df00.mp4',2);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` int NOT NULL,
  `account` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` int NOT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'2024-06-20 17:27:59','2024-06-20 17:27:59',0,'huan','1fRJqh','da448995bfcc90ae9aa59e9261c56552','huan','1403597015@qq.com',1,'G:\\resource\\image\\touxiang.jpg'),(6,'2024-06-20 17:32:00','2024-07-04 15:08:44',0,'rulai','/mwo0y','f3fa684c0d8ee7be0bfc493171b89296','如来','1090737321@qq.com',2,'G:\\resource\\image\\touxiang.jpg'),(7,'2024-06-21 10:30:05','2024-06-21 10:36:57',0,'189231941','XjBut5','60944e8cf976fae14fd8fdfd6c33dc7d','zhangsan','2105544262@qq.com',1,'G:\\resource\\image\\touxiang.jpg'),(8,'2024-06-21 14:44:46','2024-06-21 14:44:46',0,'18225016558','EQ4pM3','7800aec78ae4e5faac52b033d1e96c79','zhanghao','zh18225016558@outlook.com',2,'G:\\resource\\image\\touxiang.jpg'),(9,'2024-06-27 11:29:35','2024-06-27 11:29:35',0,'123','9gWzuj','f5c2ea5f5428b027cd641025d09a5f6e','123','1@qq.com',1,'G:\\resource\\image\\wallhaven-1kgllv.jpg'),(10,'2024-06-27 14:49:07','2024-06-27 14:49:07',0,'jj','1dE1BY','3097c17589478988635e5301af7dcc89','jj','12@qq.com',0,'G:\\resource\\image\\touxiang.jpg'),(11,'2024-06-27 14:58:42','2024-06-27 14:58:42',0,'jjj','0GZtQH','3a95b928cdaeb9c60b3f8071bc032ced','jjj','1090731@qq.com',0,'G:\\resource\\image\\touxiang.jpg'),(12,'2024-06-29 11:09:01','2024-06-29 11:09:01',0,'yuan','Liomvg','1aa76f7759560742c70eb7622bcb4c3e','yuan','109073321@qq.com',0,'G:\\resource\\image\\wallhaven-y8233x.jpg'),(17,'2024-06-29 11:33:02','2024-06-29 11:33:02',0,'hhh','LZUrx4','cef04bb39a2f4b38c6c789cc3e5e375e','hhh','udsweetounsec790@gmail.com',1,'G:\\resource\\image\\wallhaven-e7j33o.jpg'),(18,'2024-06-29 14:43:48','2024-06-29 14:43:48',0,'tiant','/qMAa7','58bec7ab60e96fe7874e5ee719a90e14','123','3255814368@qq.com',1,'G:\\resource\\image\\ye.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-04 15:54:19
