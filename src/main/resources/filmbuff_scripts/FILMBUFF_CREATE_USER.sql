#---------CREATE TABLE USER-----------#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `filmbuff`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(50) NULL,
  `password` VARCHAR(100) NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`));
