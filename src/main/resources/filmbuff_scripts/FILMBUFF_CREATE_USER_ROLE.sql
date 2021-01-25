#---------CREATE TABLE USER_ROLE-----------#

CREATE TABLE `filmbuff`.`user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`));