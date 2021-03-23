#---------CREATE TABLE PERMISSION-----------#

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `filmbuff`.`permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `permission_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));