#---------CREATE TABLE QUIZ_QUESTION-----------#

DROP TABLE IF EXISTS `quiz_question`;
CREATE TABLE `filmbuff`.`quiz_question` (
  `quiz_id` INT NOT NULL,
  `question_id` INT NOT NULL,
  PRIMARY KEY (`quiz_id`, `question_id`),
  INDEX `fk_quiz_question_question_id_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_quiz_question_quiz_id`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `filmbuff`.`quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_quiz_question_question_id`
    FOREIGN KEY (`question_id`)
    REFERENCES `filmbuff`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
