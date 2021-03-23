#---------CREATE TABLE QUIZ-----------#

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `filmbuff`.`quiz` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_id` INT NULL,
  `time_limit` INT NULL,
  `date` DATE NULL,
  `start_time` DATETIME NULL,
  `created_by` INT NULL,
  `question_limit` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_quiz_user_created_by_idx` (`created_by` ASC) VISIBLE,
  INDEX `fk_quiz_category_category_id_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_quiz_user_created_by`
    FOREIGN KEY (`created_by`)
    REFERENCES `filmbuff`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_quiz_category_category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `filmbuff`.`category` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);
