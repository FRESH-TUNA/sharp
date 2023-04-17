ALTER TABLE `stock` ADD `has_expire` TINYINT(1) NOT NULL;
ALTER TABLE `stock` ADD `expire_date` DATETIME NOT NULL;
ALTER TABLE `stock` ADD `has_manufacture` TINYINT(1) NOT NULL;
ALTER TABLE `stock` ADD `manufacture_date` DATETIME NOT NULL;
