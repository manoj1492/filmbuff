#---------CREATE TABLE QUESTION-----------#


CREATE TABLE `filmbuff`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `statement` TEXT NULL,
  `category_id` INT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_category_category_id_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_category_category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `filmbuff`.`category` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);
