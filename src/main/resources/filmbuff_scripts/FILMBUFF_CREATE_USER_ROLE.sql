#---------CREATE TABLE USER_ROLE-----------#

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `filmbuff`.`user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`));