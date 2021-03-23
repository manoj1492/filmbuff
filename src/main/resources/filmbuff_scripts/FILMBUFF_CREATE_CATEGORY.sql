#---------CREATE TABLE CATEGORY-----------#

DROP TABLE IF EXISTS `category`;
CREATE TABLE `filmbuff`.`category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `icon` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));
