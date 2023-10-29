CREATE TABLE IF NOT EXISTS `item_combo` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `parent_item_id` bigint(20) NOT NULL,
    `combo_item_id` bigint(20) NOT NULL,

    `amount` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
