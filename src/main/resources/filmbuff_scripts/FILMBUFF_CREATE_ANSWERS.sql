#---------CREATE TABLE ANSWERS-----------#

CREATE TABLE `filmbuff`.`answers` (
  `question_id` INT NOT NULL,
  `choice_id` INT NOT NULL,
  `is_correct` TINYINT NULL,
  INDEX `fk_answers_question_idx` (`question_id` ASC) VISIBLE,
  INDEX `fk_answers_choice_idx` (`choice_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_question`
    FOREIGN KEY (`question_id`)
    REFERENCES `filmbuff`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answers_choice`
    FOREIGN KEY (`choice_id`)
    REFERENCES `filmbuff`.`choice` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);