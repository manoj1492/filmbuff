#---------CREATE TABLE ANSWER-----------#

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `filmbuff`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
