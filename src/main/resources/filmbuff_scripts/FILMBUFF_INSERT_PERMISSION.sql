LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'QUIZ_READ'),(2,'QUIZ_WRITE'),(3,'USER_READ'),(4,'USER_WRITE');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;