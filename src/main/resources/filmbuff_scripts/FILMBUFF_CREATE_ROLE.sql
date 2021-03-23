#---------CREATE TABLE ROLE-----------#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `filmbuff`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));