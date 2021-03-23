#---------CREATE TABLE USER-----------#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `filmbuff`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(50) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(100) NULL,
  `enabled` TINYINT(1) NULL,
  PRIMARY KEY (`id`));
