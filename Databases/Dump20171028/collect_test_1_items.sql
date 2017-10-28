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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `he_name` varchar(255) NOT NULL,
  `en_name` varchar(255) NOT NULL,
  `category` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'תחתונים','underpants',0),(2,'שמלה','dress',0),(3,'צעיף','scarf',0),(4,'פיג\'מה','pajamas',0),(5,'סנדלים','sandals',0),(6,'נעליים לטיולים','Hiking shoes',0),(7,'כפכפים','Flip flops',0),(8,'כפפות','Gloves',0),(9,'נעליים ליום יום','Shoes for daily use',0),(10,'משקפי ראייה ו/או עדשות מגע','Eyeglasses and / or contact lenses',0),(11,'משקפי שמש','Sunglasses',0),(12,'מכנס תרמי','Thermal Pants',0),(13,'מכנסי טיולים','Hiking pants',0),(14,'מכנסיים אלגנטיים','Elegant trousers',0),(15,'מכנסיים ארוכים','Long pants',0),(16,'מכנסיים קצרים','Short Pants',0),(17,'כובע מצחייה','Cap',0),(18,'כובע צמר','Wool Hat',0),(19,'טריינינגים','Trainings',0),(20,'חם צוואר','Hot Neck',0),(21,'חצאיות','Skirts',0),(22,'טייטס ארוך/קצר ','Long / short tights',0),(23,'חזיות','Brassieres',0),(24,'חזיות ספורט','sports bras',0),(25,'חגורת/גרב כסף','Belt / stocking money',0),(26,'חולצה תרמית','Thermal shirt',0),(27,'חולצות אלגנטיות','Elegant shirts',0),(28,'חולצות ארוכות','Long shirts',0),(29,'חולצות מנדפות זיעה','T-shirts sweat',0),(30,'חולצות קצרות','short shirts',0),(31,'ז\'קט פליס ','A high quality Fleece jacket',0),(32,'ז\'קט רגיל ','Casual jacket for day and night',0),(33,'גרביים','Socks',0),(34,'גטקס','Getx',0),(35,'גופיות','tank tops',0),(36,'ג\'ינס','Jeans',0),(37,'בוקסר','Boxer',0),(38,'בגד ים','Swimsuit',0),(39,'תיק צד לערב ','Evening bag',0),(40,'בגדים חמים ללילה','Warm clothes for the night',0),(41,'קרדיגן','Cardigan',0),(42,'תכשיטים','jewelry',0),(43,'דרכון','passport',1),(44,'חגורת/גרב כסף','Belt / stocking money',1),(45,'פנקס','notebook',1),(46,'פנקס חיסונים','Record of immunizations',1),(47,'מדריך טיול','Tour Guide',1),(48,'המחאת נוסעים','Travel Chicks',1),(49,'כרטיס אשראי בינלאומי','International credit card',1),(50,'כרטיס נוסע מתמיד ','Frequent Flyer Card',1),(51,'צילומים של כל המסמכים ','Photos of all documents',1),(52,'כרטיסי טיסה','flight tickets',1),(53,'מילון-שיחון','Dictionary-Phrasebook',1),(54,'רישיון נהיגה','Driving License',1),(55,'תווית זיהוי לתיק הקטן והגדול','Small and large bag identification label',1),(56,'פוליסת ביטוח','insurance policy',1),(57,'תכשיר נגד יתושים','Mosquito repellent',2),(58,'תרופות נגד אלרגיות','Antihypertensive drugs',2),(59,'תחבושת אלסטית','Elastic bandage',2),(60,'צמר גפן','Cotton',2),(61,'פלסטרים','Plasters',2),(62,'סיכות ביטחון','Safety pins',2),(63,'משאף ','inhaler',2),(64,'משחה לשפשפות','Ointment for rubbing',2),(65,'משחה נגד כוויות – אלוורה','Ointment for burns',2),(66,'כדורים להורדת חום וצינון','Pills for lowering heat and cooling',2),(67,'כדורים להקלה מצרבת','Heartburn relief pills',2),(68,'כדורים להקלה על כאבי גרון','Pills for relieving sore throat',2),(69,'כדורים למלריה','Pills for malaria',2),(70,'כדורים למניעת שלשולים וכאבי בטן','Pills to prevent diarrhea and abdominal pain',2),(71,'כדורים נגד בחילות','Anti-nausea pills',2),(72,'כדורים נגד כאבי ראש','Headache pills',2),(73,'חומר חיטוי','Disinfectant',2),(74,'וזלין','vaseline',2),(75,'גלולות','Pills',2),(76,'אמצעי מניעה','Contraception',2),(78,'מדחום','Thermometer',2),(79,'תחבושת אישית','personal bandage',2),(80,'איפור','Makeup',3),(81,'בושם','perfume',3),(82,'ג\'ל לשטיפת ידיים','Hand wash gel',3),(83,'גומיות לשיער','Hair bands',3),(84,'דאודורנט','deodorant',3),(85,'טישו','Tissue',3),(86,'מברשת שיניים','Toothbrush',3),(87,'מגבונים לחים','wet wipes',3),(88,'מסרק/מברשת שיער','Comb / hair brush',3),(89,'מקלוני אוזניים','Q-tips',3),(90,'מראה קטנה','A small mirror',3),(91,'משחת שיניים','toothpaste',3),(92,'משייף ציפורניים','Sanding nails',3),(93,'סבון לגוף','Body Soap',3),(94,'סבון לפנים','Facial soap',3),(95,'סכין גילוח + קצף גילוח/מכונת גילוח','Razor + shaving cream / shaver',3),(96,'פינצטה','Tweezers',3),(97,'תחתוניות','Panties',3),(98,'קונדישינר','Confectionery',3),(99,'קוצץ ציפורניים/מספריים לציפורניים','Nail clippers / nail scissors',3),(100,'קרם גוף','body cream',3),(101,'קרם לשיער','Hair cream',3),(102,'קרם פנים','face cream',3),(103,'קרם הגנה משמש','Sunscreen',3),(104,'שמפו','shampoo',3),(105,'תחבושות היגייניות/טמפונים','Sanitary napkins / tampons',3),(106,'פן או מחליק שיער אחר','Pen or other hair straightener',3),(107,'מים לעדשות','Water for lenses',3),(108,'מספריים','Scissors',3),(109,'אוזניות','headphones',4),(110,'גנרטור ','generator',4),(111,'מוזיקה – נגן MP3 + רמקול','Music - MP3 player + speaker',4),(112,'מחשב נייד קטן או טאבלט','A small laptop or tablet',4),(113,'מטען/מטען נייד','Charger / charger',4),(114,'מפצל','Splitter',4),(115,'מצלמה','Camera',4),(116,'סוללות','Batteries',4),(117,'נורה','light bulbs',4),(118,'תדיאור – פנס חירום','TADYOR - Emergency Flashlight',4),(119,'מתאמים שונים לחשמל','Various adapters for electricity',4),(120,'פלאפון','Cell Phones',4),(121,'אגוזים, שקדים וכו\'','Nuts, almonds, etc.',5),(122,'חטיפי גרנולה','Granola bars',5),(123,'כוסות','Cups',5),(124,'מים','water',5),(125,'סיר','pot',5),(126,'סכו\"ם','amount',5),(127,'צלחות','Plates',5),(128,'קבנוס','Kabanus',5),(129,'שולחן מתקפל','folding table',5),(130,'שימורים ','Canned food',5),(131,'שמן','oil',5),(132,'שקיות זבל + שקיות ניילון','Garbage bags + plastic bags',5),(133,'תבשילים לבישול מהיר ','Quick-cooking dishes',5),(134,'סנדוויצ\'ים ליום הראשון','Sandwiches for the first day',5),(135,'מלח, פלפל ותבלינים','Salt, pepper and spices',5),(136,'סבון כלים + סקוטצ\'','Soap Dish',5),(137,'שק שינה','Sleeping Bag',6),(138,'שמיכה נוספת','Another blanket',6),(139,'קופסא למברשת שיניים','A toothbrush box',6),(140,'פינג\'אן ','Finjan',6),(141,'ערסל','hammock',6),(143,'משקפת','binoculars',6),(144,'מצפן','compass',6),(145,'מקל לטיולים ','Stick for trips',6),(146,'מנעולים לתיקים','Locks for bags',6),(147,'מחצלת ','mat',6),(148,'מזרון מתנפח זוגי/יחיד','Inflatable double / single mattress',6),(149,'מגבת קטנה ','A small towel',6),(150,'לדרמן ','Lederman',6),(151,'כרית קטנה','A small pillow',6),(152,'כילה נגד יתושים ','Mosquito net',6),(153,'כיסא מתקפל','folding chair',6),(154,'אוהל יתדות ומוטות','tent Pegs and rods',6),(155,'חבלים','Ropes',6),(156,'גזיה ','Gasier ',6),(157,'אולר ','Switchblade',6),(158,'אטמי אוזניים ','Earplugs',7),(159,'ארנק','Purse',7),(160,'כיסוי עיניים לשינה ','Eyecover for sleep',7),(161,'כלי כתיבה ','Writing Tools',7),(162,'כסף מזומן ','Cash',7),(163,'כרית שינה קטנה למטוס','A small sleeping pad for the plane',7),(164,'מגבות לרחצה ולים','Towels for bathing and sea',7),(165,'מחברת קטנה ועט','Small notebook and pen',7),(166,'מצית וגפרורים','Lighter and matches',7),(167,'ספר ','Book',7),(168,'ערכת תפירה','Sewing kit',7),(169,'פטיש','hammer',7),(170,'ציוד דייג','Fishing Equipment',7),(171,'ציליה','Tzilla',7),(172,'קלפים','Cards',7),(173,'שמשיה לים','Sunshade to the sea',7),(174,'שק כביסה','Laundry bag',7),(175,'מזון לתינוק','Baby food',5),(176,'בקבוק לתינוק','Baby bottle',5),(177,'שמיכה לתינוק','Baby blanket',7),(178,'תפילין','tefillin',7),(179,'טיטולים לתינוק','Baby diapers',3),(180,'תיק לטיול יומי','Dayli Bag',6),(181,'תרופות קבועות','Dayli Pills',2),(182,'ציוד סקי','Ski Equipment',7),(183,'עגלת תינוק','Baby carriage',7);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-28 21:53:17
