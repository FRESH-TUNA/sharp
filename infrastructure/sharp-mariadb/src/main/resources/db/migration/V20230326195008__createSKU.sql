CREATE TABLE IF NOT EXISTS `sku` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `name` varchar(100) NOT NULL,
    `barcode` varchar(100) NOT NULL,
    `description` varchar(100) NOT NULL,

    `price` DECIMAL(10,2) NOT NULL,
    `currency` varchar(10) NOT NULL,

    `width` DECIMAL(10,2) NOT NULL,
    `height` DECIMAL(10,2) NOT NULL,
    `depth` DECIMAL(10,2) NOT NULL,
    `dimension_scale` varchar(10) NOT NULL,

    `weight_value` DECIMAL(10,2) NOT NULL,
    `weight_scale` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
