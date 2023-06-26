CREATE TABLE IF NOT EXISTS `seller` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,
    `public_id` BINARY(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
