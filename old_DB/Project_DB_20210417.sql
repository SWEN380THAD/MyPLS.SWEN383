drop database if exists `swenproject`;
CREATE DATABASE  IF NOT EXISTS `swenproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `swenproject`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: swenproject
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
-- Table structure for table `courses`
--

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

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'This course gives you a very basic introduction to the Java programing language.  we will cover everything from setting up your computer to deploying an application.  this is an update','Access to pc or MAC and internet','No prereqs needed','Introduction to Java'),(9,'this course will cover the basics of creating your first Python application.  from setting up your computer, writing the code and on to implementing your application.','access to computer and internet','java and javascript','Python'),(19,'This course gives you a very basic introduction to the Java Script programing language. we will cover everything from setting up your computer to deploying an application..','access to pc or MAC and internet','Java, HTML','Introduction to Java Script'),(24,'This course gives you a very basic introduction to the Java programing language. we will cover everything from setting up your computer to deploying an application..','Access to pc or MAC and internet','No prereqs needed ','HTML'),(25,'This course gives you a very basic introduction to the Java programing language. we will cover everything from setting up your computer to deploying an application..','Access to pc or MAC and internet','Java, HTML and Javascript','Introduction to Scala'),(30,'can we pelase test this more and more and more','this is jsut a test','some other tests','more testing '),(33,'this is only a test','this is fun','test test','more testing for testing'),(34,'this is just a testing course','test','test','course for no fun');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussiongroups`
--

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

--
-- Dumping data for table `discussiongroups`
--

LOCK TABLES `discussiongroups` WRITE;
/*!40000 ALTER TABLE `discussiongroups` DISABLE KEYS */;
INSERT INTO `discussiongroups` VALUES (1,'HTML Breakthroughs in the past 10 years','please share your thoughts on this.','2021-03-19 11:53:13','1','1'),(2,'Java Vs JavaScript','what do you like better?  why?  be specific.','2021-03-23 08:17:16','1','1'),(3,'we can test this discussion','what is this?','2021-04-01 08:31:26','1','1');
/*!40000 ALTER TABLE `discussiongroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

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

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (4,'this coiurse was great',9,1,3,'course'),(5,'this lesson was great',7,1,4,'lesson');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_users`
--

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

--
-- Dumping data for table `group_users`
--

LOCK TABLES `group_users` WRITE;
/*!40000 ALTER TABLE `group_users` DISABLE KEYS */;
INSERT INTO `group_users` VALUES (2,3,'1'),(3,3,'1'),(2,4,'1'),(3,4,'1'),(1,5,'1'),(2,5,'1'),(3,5,'1'),(1,6,'1'),(1,7,'1'),(2,7,'1'),(1,8,'1'),(1,10,'1'),(2,10,'1'),(3,10,'1');
/*!40000 ALTER TABLE `group_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `minimum_score` int NOT NULL DEFAULT '0',
  `course_id` int NOT NULL,
  `order` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`lesson_id`),
  KEY `fk_lesson_courseid_idx` (`course_id`),
  CONSTRAINT `fk_lesson_courseid` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (1,'what is java?',65,1,1),(2,'Java Structure',65,1,2),(3,'Java classes',65,1,3),(4,'Java methods',65,1,4);
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `media_id` int NOT NULL,
  `lecture_id` int DEFAULT NULL,
  `media_link` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  KEY `fk_media_lesson_id_idx` (`lecture_id`),
  CONSTRAINT `fk_media_lesson_id` FOREIGN KEY (`lecture_id`) REFERENCES `lessons` (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_answers`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_answers`
--

LOCK TABLES `quiz_answers` WRITE;
/*!40000 ALTER TABLE `quiz_answers` DISABLE KEYS */;
INSERT INTO `quiz_answers` VALUES (37,15,'always',1),(38,15,'yes',0),(39,15,'no',0),(40,15,'sometimes',0),(41,16,'1 hour',1),(42,16,'too long',0),(43,16,'soon',0),(44,16,'1 min',0),(45,17,'4',1),(46,17,'1',0),(47,17,'2',0),(48,17,'3',0),(49,18,'23',1),(50,18,'12',0),(51,18,'55',0),(52,18,'66',0);
/*!40000 ALTER TABLE `quiz_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_questions`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_questions`
--

LOCK TABLES `quiz_questions` WRITE;
/*!40000 ALTER TABLE `quiz_questions` DISABLE KEYS */;
INSERT INTO `quiz_questions` VALUES (15,' does this work',11),(16,'how long',11),(17,'guess number',12),(18,'guess age',12);
/*!40000 ALTER TABLE `quiz_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_scores`
--

DROP TABLE IF EXISTS `quiz_scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_scores` (
  `user_id` int NOT NULL,
  `quiz_id` int NOT NULL,
  `quiz_score` int NOT NULL,
  `attempt_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`quiz_id`,`attempt_date`),
  KEY `fk_quizscore_userid_idx` (`user_id`),
  CONSTRAINT `fk_quizscore_quizid` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`),
  CONSTRAINT `fk_quizscore_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_scores`
--

LOCK TABLES `quiz_scores` WRITE;
/*!40000 ALTER TABLE `quiz_scores` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz_scores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `quiz_id` int NOT NULL AUTO_INCREMENT,
  `quiz_name` varchar(45) NOT NULL,
  `lesson_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (11,'HTML',0),(12,'HTML Breakthroughs in the past 10 years',0);
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `updates`
--

DROP TABLE IF EXISTS `updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `updates` (
  `update_id` int NOT NULL AUTO_INCREMENT,
  `update` varchar(45) DEFAULT NULL,
  `user_id_fk` int DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `group_id_fk` int DEFAULT '0',
  `ip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`update_id`),
  KEY `user_id_fk` (`user_id_fk`),
  KEY `updates_ibfk_2` (`group_id_fk`),
  CONSTRAINT `updates_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`),
  CONSTRAINT `updates_ibfk_2` FOREIGN KEY (`group_id_fk`) REFERENCES `discussiongroups` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `updates`
--

LOCK TABLES `updates` WRITE;
/*!40000 ALTER TABLE `updates` DISABLE KEYS */;
/*!40000 ALTER TABLE `updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_courses`
--

DROP TABLE IF EXISTS `user_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_courses` (
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`course_id`),
  KEY `fk_course_user_id_idx` (`course_id`),
  CONSTRAINT `fk_course_user_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_course_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_courses`
--

LOCK TABLES `user_courses` WRITE;
/*!40000 ALTER TABLE `user_courses` DISABLE KEYS */;
INSERT INTO `user_courses` VALUES (3,1),(7,1),(6,9),(7,19),(8,24),(8,25),(8,33),(8,34);
/*!40000 ALTER TABLE `user_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_lesson`
--

DROP TABLE IF EXISTS `user_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_lesson` (
  `lesson_id` int NOT NULL DEFAULT '0',
  `user_id` int NOT NULL,
  `lesson_score` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`lesson_id`),
  KEY `fk_lesson_userid_idx` (`user_id`),
  CONSTRAINT `fk_userlesson_lessonid` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`),
  CONSTRAINT `fk_userlesson_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_lesson`
--

LOCK TABLES `user_lesson` WRITE;
/*!40000 ALTER TABLE `user_lesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL,
  `status` enum('0','1') DEFAULT '0',
  `verificationCode` varchar(64) DEFAULT NULL,
  `LName` varchar(45) DEFAULT NULL,
  `FName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','test@rit.com','Admin','0','0',NULL,NULL),(4,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','test@rit.edu','Admin','0','0',NULL,NULL),(5,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','test@rit.org','Learner','0','0',NULL,NULL),(6,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','demo@localhost','Professor','0','0','Test1','Dave'),(7,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','demo@localhost.edu','Professor','0','0','Test2','Harsh'),(8,'cd1e05e2ff79f088be032ffcbc5f1a98e8ab37d3','demo@localhost.rit.edu','Professor','0','0','Test3','Taylor'),(9,'3617c04af9474069a13e95ebdb004aee7f1365a8','demo@localhost.rit.edus','Admin','0','0',NULL,NULL),(10,'388ad1c312a488ee9e12998fe097f2258fa8d5ee','test@rit.eduddddd','Learner','0','0',NULL,NULL),(27,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','testme@rit.edu','Professor','0','0',NULL,NULL),(29,'40bd001563085fc35165329ea1ff5c5ecbdbbeef','antihero1938@yahoo.com','Professor','1','0',NULL,NULL);
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

-- Dump completed on 2021-04-17 22:31:03
