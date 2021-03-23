#---------CREATE TABLE QUESTION_ANSWER-----------#

DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `filmbuff`.`question_answer` (
  `question_id` INT NOT NULL,
  `answer_id` INT NOT NULL,
  `is_correct` TINYINT NULL,
  PRIMARY KEY (`question_id`, `answer_id`),
  INDEX `fk_question_answer_answer_id_idx` (`answer_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_answer_question_id`
    FOREIGN KEY (`question_id`)
    REFERENCES `filmbuff`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_answer_answer_id`
    FOREIGN KEY (`answer_id`)
    REFERENCES `filmbuff`.`answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
