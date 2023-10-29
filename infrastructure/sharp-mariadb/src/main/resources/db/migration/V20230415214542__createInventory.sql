CREATE TABLE IF NOT EXISTS `inventory` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `sku_id` bigint(20) NOT NULL,
    `status` varchar(30) NOT NULL,
    `count` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
