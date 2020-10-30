#---------ALTER TABLE QUESTION-----------#

ALTER TABLE `filmbuff`.`question` 
ADD COLUMN `type` VARCHAR(45) NULL AFTER `category_id`;
