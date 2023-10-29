CREATE TABLE IF NOT EXISTS `item` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `seller_id` bigint(20) NOT NULL,

    `name` varchar(100) NOT NULL,
    `category` varchar(100) NOT NULL,

    `sku_id` bigint(20) NOT NULL,
    `description` text NOT NULL,

    `is_combo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
