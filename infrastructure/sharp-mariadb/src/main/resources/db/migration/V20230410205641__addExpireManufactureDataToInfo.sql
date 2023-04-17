ALTER TABLE `stock_info` ADD `has_expire` TINYINT(1) NOT NULL;
ALTER TABLE `stock_info` ADD `expire_date` DATETIME NOT NULL;
ALTER TABLE `stock_info` ADD `has_manufacture` TINYINT(1) NOT NULL;
ALTER TABLE `stock_info` ADD `manufacture_date` DATETIME NOT NULL;
