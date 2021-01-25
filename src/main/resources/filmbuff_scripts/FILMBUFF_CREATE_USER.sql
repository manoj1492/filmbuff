#---------CREATE TABLE USER-----------#

CREATE TABLE `filmbuff`.`new_table` (
  `id` INT NOT NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(50) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(100) NULL,
  `enabled` TINYINT(1) NULL,
  PRIMARY KEY (`id`));
