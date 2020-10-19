#---------ALTER TABLE QUIZ-----------#

set foreign_key_checks = 1; 

ALTER TABLE `filmbuff`.`quiz` 
CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;