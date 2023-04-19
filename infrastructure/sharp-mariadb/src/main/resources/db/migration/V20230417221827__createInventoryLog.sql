CREATE TABLE IF NOT EXISTS `inventory_log` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `sku_id` bigint(20) NOT NULL,

    `type` varchar(10) NOT NULL,
    `reason` varchar(10) NOT NULL,
    `condition` varchar(10) NOT NULL,

    `count` bigint(20) NOT NULL,
    `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



