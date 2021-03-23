#---------CREATE TABLE ROLE_PERMISSION-----------#

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `filmbuff`.`role_permission` (
  `role_id` INT NOT NULL,
  `permission_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`));