#---------CREATE TABLE QUIZ_PARTICIPANTS-----------#

CREATE TABLE `filmbuff`.`quiz_participants` (
  `user_id` INT NOT NULL,
  `quiz_id` INT NOT NULL,
  `correct_count` INT NULL,
  `is_winner` TINYINT NULL,
  `is_entered` TINYINT NULL,
  INDEX `fk_quiz_participants_quiz_idx` (`quiz_id` ASC) VISIBLE,
  CONSTRAINT `fk_quiz_participants_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `filmbuff`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_quiz_participants_quiz`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `filmbuff`.`quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
