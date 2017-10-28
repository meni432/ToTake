-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: totake.website    Database: collect_test_1
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `destination_id` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `other_items` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,8,24,'male',7,NULL),(2,8,24,'female',7,NULL),(3,8,17,'female',7,NULL),(4,8,61,'male',7,NULL),(5,10,38,'female',7,NULL),(6,8,38,'female',30,NULL),(7,8,51,'male',30,NULL),(8,8,34,'female',7,NULL),(9,4,999,'male',14,NULL),(10,4,49,'female',7,NULL),(11,7,50,'male',7,NULL),(12,3,999,'male',30,NULL),(13,3,999,'female',14,NULL),(14,1,999,'male',60,NULL),(15,17,999,'male',60,'בדיקה מני, בדיקה 2 מני'),(16,2,999,'male',7,''),(17,3,999,'female',14,''),(18,3,23,'female',60,'במבה '),(19,14,26,'male',7,''),(20,8,25,'male',14,''),(21,8,23,'female',7,''),(22,3,24,'male',60,''),(23,3,23,'male',60,''),(24,8,26,'male',7,''),(25,16,25,'male',60,''),(26,10,22,'male',30,''),(27,8,24,'male',14,''),(28,3,27,'male',60,''),(29,3,23,'female',60,''),(30,15,23,'female',7,''),(31,16,52,'female',14,''),(32,14,24,'female',60,''),(33,14,23,'male',60,''),(34,3,27,'male',60,''),(35,10,21,'male',30,''),(36,4,20,'male',14,'כובע טמבל'),(37,10,20,'male',60,''),(38,3,32,'male',14,''),(39,10,25,'male',7,'עגלת תינוק'),(40,2,11,'female',14,''),(41,16,25,'male',60,''),(42,8,24,'female',7,''),(43,4,20,'female',14,''),(44,8,24,'male',7,''),(45,10,27,'male',7,''),(46,8,25,'male',7,''),(47,15,24,'male',14,''),(48,8,24,'male',14,''),(49,14,24,'female',60,''),(50,11,27,'male',60,''),(51,11,30,'male',60,''),(52,7,25,'male',7,''),(53,8,26,'male',7,''),(54,3,26,'male',30,''),(55,8,25,'male',7,''),(56,8,26,'male',7,''),(57,3,25,'male',60,''),(58,5,26,'male',14,''),(59,2,999,'female',30,'drop tables *'),(60,2,28,'male',30,'סידור | פריזבי | מטקות | כלי נגינה | סיגריות | מזלג חשמלי'),(61,3,23,'male',7,''),(62,10,26,'male',7,''),(63,10,28,'male',60,''),(64,7,23,'female',14,''),(65,8,25,'male',7,'כרטיס סטודנט'),(66,10,26,'male',7,''),(67,16,26,'male',30,''),(68,14,24,'female',30,''),(69,10,10,'male',7,''),(70,14,26,'male',60,''),(71,14,23,'male',60,''),(72,8,26,'male',14,''),(73,8,46,'male',7,''),(74,10,23,'male',7,''),(75,11,23,'male',60,''),(76,2,118,'male',60,'ירוק'),(77,3,23,'female',30,''),(78,15,26,'male',60,''),(79,11,26,'female',7,''),(80,14,24,'female',60,''),(81,8,24,'female',7,''),(82,8,24,'male',7,''),(83,7,23,'female',7,''),(84,3,24,'male',60,''),(85,3,26,'male',60,''),(86,3,26,'male',30,''),(87,7,24,'female',7,''),(88,3,23,'male',60,''),(89,3,22,'male',30,''),(90,10,22,'male',7,''),(91,3,22,'female',60,''),(92,8,24,'female',7,''),(93,8,25,'male',7,''),(94,10,19,'male',7,''),(95,8,26,'male',7,''),(96,3,24,'male',30,''),(97,8,28,'male',7,''),(98,8,26,'male',7,''),(99,10,25,'male',30,''),(100,16,28,'male',60,''),(101,10,26,'male',7,''),(102,6,24,'male',60,''),(103,10,22,'male',3,''),(104,8,26,'female',7,''),(105,8,34,'female',7,''),(106,10,30,'female',3,''),(107,14,29,'female',60,''),(108,8,26,'female',7,''),(109,8,56,'female',7,''),(110,10,30,'female',3,''),(111,16,53,'female',14,''),(112,3,34,'female',30,''),(113,8,28,'female',7,''),(114,7,27,'female',14,''),(115,8,35,'female',14,''),(116,4,19,'female',3,''),(117,8,24,'female',60,''),(118,10,28,'male',3,''),(119,7,19,'female',14,''),(120,8,26,'male',7,''),(121,10,31,'female',3,''),(122,14,20,'female',30,''),(123,8,36,'female',14,''),(124,13,28,'female',30,''),(125,8,18,'female',7,''),(126,7,23,'male',60,''),(127,16,42,'male',14,''),(128,3,23,'male',60,''),(129,8,23,'female',7,''),(130,14,24,'male',30,''),(131,7,68,'male',7,''),(132,3,41,'male',14,''),(133,14,22,'female',60,''),(134,3,33,'female',14,'כדאי להוסיף דברים למשפחה עם ילדים ותינוקות: נורפן לילדים, חולצות ומכנסי תינוקות,  מוצץ, בקבוק, קרם הגנה לילדים,  מטרנה, צעצועים, מחשב עם סרטי ילדים לטיסה, ממתקים לילדים כפית לילדים'),(135,3,36,'female',60,'פהיסטיל ג\'ל לעקיצות, קרש חיתוך, מחבת, אטבי כביסה וחבל דק, '),(136,10,26,'male',3,''),(137,17,120,'male',60,''),(138,1,120,'female',3,''),(139,3,999,'female',14,'למחוקקקקקקק'),(140,14,24,'male',60,''),(141,15,23,'female',14,''),(142,4,25,'female',30,''),(143,3,25,'male',60,''),(144,3,23,'female',60,''),(145,11,25,'male',60,''),(146,1,24,'male',30,''),(147,14,25,'male',60,''),(148,3,24,'male',60,'קונדומים'),(149,3,25,'male',60,''),(150,10,23,'female',7,''),(151,1,28,'male',60,''),(152,3,23,'female',60,''),(153,1,28,'male',14,''),(154,8,23,'female',7,''),(155,8,30,'female',3,''),(156,3,23,'male',60,''),(157,3,23,'male',60,'מגבת מנדפת'),(158,3,22,'male',60,''),(159,3,24,'male',30,''),(160,3,25,'male',30,''),(161,3,24,'female',30,''),(162,10,27,'male',3,''),(163,8,27,'male',7,''),(164,3,23,'female',60,''),(165,3,27,'male',7,''),(166,10,25,'male',7,''),(167,3,20,'female',60,''),(168,3,22,'male',60,''),(169,13,26,'female',7,''),(170,8,21,'female',14,''),(171,3,21,'female',30,'רזיאל המלאך. '),(172,10,22,'male',7,''),(173,8,21,'female',7,''),(174,4,22,'male',7,''),(175,7,21,'female',7,''),(176,3,25,'male',60,''),(177,3,26,'male',60,''),(178,3,24,'female',60,''),(179,3,24,'male',60,''),(180,3,26,'male',60,''),(181,4,27,'male',7,'סידור טלית ואת נאוה'),(182,4,21,'female',60,''),(183,3,23,'female',60,''),(184,8,37,'male',7,''),(185,8,31,'female',3,''),(186,7,56,'female',3,''),(187,14,36,'male',14,''),(188,7,58,'female',7,''),(189,8,54,'male',7,''),(190,7,48,'male',7,''),(191,8,45,'male',7,''),(192,2,68,'male',7,''),(193,3,23,'female',30,''),(194,10,16,'male',3,''),(195,7,52,'female',7,''),(196,8,32,'female',7,''),(197,14,25,'female',60,''),(198,8,45,'female',14,''),(199,8,52,'female',7,''),(200,10,36,'female',3,''),(201,8,43,'female',3,''),(202,8,32,'female',14,''),(203,7,37,'female',7,''),(204,10,40,'female',7,''),(205,8,35,'female',3,''),(206,8,45,'female',7,''),(207,8,45,'male',14,''),(208,10,34,'female',3,''),(209,3,42,'female',14,''),(210,3,43,'male',14,''),(211,8,44,'male',7,''),(212,8,35,'female',14,''),(213,7,40,'female',7,''),(214,4,64,'male',3,'סידור, יין לקידוש, כיפה,  בגדי שבת,  טלית  תיקון קוראים הקטן, '),(215,10,36,'male',3,'קיסמי שיניים, מנגל'),(216,1,39,'male',14,''),(217,10,45,'female',3,''),(218,6,26,'male',60,''),(219,3,23,'female',30,''),(220,2,999,'male',3,''),(221,4,64,'female',7,''),(222,3,18,'male',14,''),(223,7,40,'female',14,''),(224,3,23,'male',60,''),(225,10,10,'female',3,''),(226,8,24,'female',14,''),(227,3,25,'female',60,''),(228,8,23,'female',7,''),(229,3,23,'female',30,'במבה'),(230,8,120,'male',14,''),(231,4,26,'male',3,''),(232,3,24,'male',60,''),(233,8,23,'male',30,'');
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

-- Dump completed on 2017-10-28 21:53:25
