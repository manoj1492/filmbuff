LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Manoj','Majumdar','manojmajumdarb92@gmail.com','$2a$10$Q2wr1xomOjkBkYG0s92fheJ4a1nLDZl0V0R5RSP3UjNb2BcFm0M8W',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;