CREATE TABLE IF NOT EXISTS `sku` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `name` varchar(300) NOT NULL,
    `barcode` varchar(100) NOT NULL,
    `description` text NOT NULL,

    `price` varchar(10) NOT NULL,
    `currency` varchar(10) NOT NULL,

    `width` varchar(10) NOT NULL,
    `height` varchar(10) NOT NULL,
    `depth` varchar(10) NOT NULL,
    `dimension_scale` varchar(10) NOT NULL,

    `weight_value` varchar(10) NOT NULL,
    `weight_scale` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
