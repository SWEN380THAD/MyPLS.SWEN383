drop database if exists `swenproject`;
CREATE DATABASE  IF NOT EXISTS `swenproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `swenproject`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: swenproject
-- ------------------------------------------------------
-- Server version	8.0.19
​
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
​
--
-- Table structure for table `courses`
--
​
DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(245) DEFAULT NULL,
  `requierments` varchar(45) DEFAULT NULL,
  `prereqs` varchar(45) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`,`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `courses`
--
​
LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'This course gives you a very basic introduction to the Java programing language.  we will cover everything from setting up your computer to deploying an application.  this is an update','Access to pc or MAC and internet','No prereqs needed','Introduction to Java'),(9,'this course will cover the basics of creating your first Python application.  from setting up your computer, writing the code and on to implementing your application.','access to computer and internet','java and javascript','Python'),(19,'This course gives you a very basic introduction to the Java Script programing language. we will cover everything from setting up your computer to deploying an application..','access to pc or MAC and internet','Java, HTML','Introduction to Java Script'),(24,'This course gives you a very basic introduction to the Java programing language. we will cover everything from setting up your computer to deploying an application..','Access to pc or MAC and internet','No prereqs needed ','HTML'),(25,'This course gives you a very basic introduction to the Java programing language. we will cover everything from setting up your computer to deploying an application..','Access to pc or MAC and internet','Java, HTML and Javascript','Introduction to Scala'),(30,'can we pelase test this more and more and more','this is jsut a test','some other tests','more testing '),(33,'this is only a test','this is fun','test test','more testing for testing'),(34,'this is just a testing course','test','test','course for no fun');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `discussiongroups`
--
​
DROP TABLE IF EXISTS `discussiongroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussiongroups` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) DEFAULT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('0','1') DEFAULT '1',
  `is_public` enum('0','1') DEFAULT '1',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `discussiongroups`
--
​
LOCK TABLES `discussiongroups` WRITE;
/*!40000 ALTER TABLE `discussiongroups` DISABLE KEYS */;
INSERT INTO `discussiongroups` VALUES (1,'HTML Breakthroughs in the past 10 years','please share your thoughts on this.','2021-03-19 11:53:13','1','1'),(2,'Java Vs JavaScript','what do you like better?  why?  be specific.','2021-03-23 08:17:16','1','1'),(3,'we can test this discussion','what is this?','2021-04-01 08:31:26','1','1');
/*!40000 ALTER TABLE `discussiongroups` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `feedback`
--
​
DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `feedback` varchar(1000) NOT NULL,
  `rating` int DEFAULT NULL,
  `feedback_key` int NOT NULL,
  `user_id` int NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `fk_feedback_userid_idx` (`user_id`),
  CONSTRAINT `fk_feedback_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `feedback`
--
​
LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (4,'this coiurse was great',9,1,3,'course'),(5,'this lesson was great',7,1,4,'lesson');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `group_users`
--
​
DROP TABLE IF EXISTS `group_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_users` (
  `group_id_fk` int NOT NULL,
  `user_id_fk` int NOT NULL,
  `status` enum('0','1') DEFAULT '1',
  PRIMARY KEY (`user_id_fk`,`group_id_fk`),
  KEY `group_id_fk` (`group_id_fk`),
  CONSTRAINT `group_users_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_users_ibfk_2` FOREIGN KEY (`group_id_fk`) REFERENCES `discussiongroups` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `group_users`
--
​
LOCK TABLES `group_users` WRITE;
/*!40000 ALTER TABLE `group_users` DISABLE KEYS */;
INSERT INTO `group_users` VALUES (2,3,'1'),(3,3,'1'),(2,4,'1'),(3,4,'1'),(1,5,'1'),(2,5,'1'),(3,5,'1'),(1,6,'1'),(1,7,'1'),(2,7,'1'),(1,8,'1'),(1,10,'1'),(2,10,'1'),(3,10,'1');
/*!40000 ALTER TABLE `group_users` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `lessons`
--
​
DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(45) DEFAULT NULL,
  `minimum_score` int NOT NULL DEFAULT '0',
  `course_id` int DEFAULT NULL,
  `lesson_order` int NOT NULL DEFAULT '0',
  `startDate` varchar(45) DEFAULT NULL,
  `endDate` varchar(45) DEFAULT NULL,
  `lesson_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `lessons`
--
​
LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (1,'what is java?',65,1,1,NULL,NULL,'we will teach you what java really is and how fun it is'),(2,'Java Structure',65,1,2,NULL,NULL,'the best in structures.  your gonna love it!'),(3,'Java classes',65,1,3,NULL,NULL,'classes are always fun.  you will have classes coming out of your ears!'),(4,'Java methods',65,1,4,NULL,NULL,'we really dont know what these do?'),(8,'HTML 5',65,1,0,'2021-04-20','2021-04-23','all the reasons why people dislike coding');
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `media`
--
​
DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `media_id` int NOT NULL AUTO_INCREMENT,
  `lecture_id` int DEFAULT NULL,
  `media_link` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  KEY `fk_media_lesson_id_idx` (`lecture_id`),
  CONSTRAINT `fk_media_lesson_id` FOREIGN KEY (`lecture_id`) REFERENCES `lessons` (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `media`
--
​
LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,1,'src/main/resources/static/multimedia/UML class.pdf'),(2,1,'src/main/resources/static/multimedia/UML class.pdf'),(3,1,'src/main/resources/static/multimedia/UML class.pdf'),(4,1,'src/main/resources/static/multimedia/project2.mp4'),(5,1,'src/main/resources/static/multimedia/CardGame_Design Document.pdf'),(6,1,'src/main/resources/static/multimedia/project2.mp4'),(7,1,'src/main/resources/static/multimedia/project2.mp4');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `quiz_answers`
--
​
DROP TABLE IF EXISTS `quiz_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_answers` (
  `answer_id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NOT NULL,
  `answer` varchar(245) NOT NULL,
  `correct` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`answer_id`),
  KEY `fk_quizanswers_questionid_idx` (`question_id`),
  CONSTRAINT `fk_quizanswers_questionid` FOREIGN KEY (`question_id`) REFERENCES `quiz_questions` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `quiz_answers`
--
​
LOCK TABLES `quiz_answers` WRITE;
/*!40000 ALTER TABLE `quiz_answers` DISABLE KEYS */;
INSERT INTO `quiz_answers` VALUES (37,15,'always',1),(38,15,'yes',0),(39,15,'no',0),(40,15,'sometimes',0),(41,16,'1 hour',1),(42,16,'too long',0),(43,16,'soon',0),(44,16,'1 min',0),(45,17,'4',1),(46,17,'1',0),(47,17,'2',0),(48,17,'3',0),(49,18,'23',1),(50,18,'12',0),(51,18,'55',0),(52,18,'66',0),(53,19,'Tuesday',1),(54,19,'Monday',0),(55,19,'Wednesday',0),(56,19,'Friday',0),(57,20,'yes',1),(58,20,'no',0),(59,20,'sometimes',0),(60,20,'maye',0),(61,21,'everything',1),(62,21,'dont know',0),(63,21,'something',0),(64,21,'nothing',0),(65,22,'sometimes',1),(66,22,'yes',0),(67,22,'no',0),(68,22,'maybe',0);
/*!40000 ALTER TABLE `quiz_answers` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `quiz_questions`
--
​
DROP TABLE IF EXISTS `quiz_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(245) NOT NULL,
  `quiz_id` int NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `fk_quizquestion_quizid_idx` (`quiz_id`),
  CONSTRAINT `fk_quizquestion_quizid` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `quiz_questions`
--
​
LOCK TABLES `quiz_questions` WRITE;
/*!40000 ALTER TABLE `quiz_questions` DISABLE KEYS */;
INSERT INTO `quiz_questions` VALUES (15,' does this work',11),(16,'how long',11),(17,'guess number',12),(18,'guess age',12),(19,'what day is it?',13),(20,' does this work',13),(21,'what is it?',14),(22,' does this work',14);
/*!40000 ALTER TABLE `quiz_questions` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `quiz_scores`
--
​
DROP TABLE IF EXISTS `quiz_scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 *...