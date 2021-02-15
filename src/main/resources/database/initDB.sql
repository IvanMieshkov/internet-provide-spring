

CREATE TABLE IF NOT EXISTS `users` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `full_name_en` varchar(60) DEFAULT NULL,
                                       `full_name_ukr` varchar(60) DEFAULT NULL,
                                       `password` varchar(65) DEFAULT NULL,
                                       `email` varchar(45) DEFAULT NULL,
                                       `address` varchar(250) DEFAULT NULL,
                                       `phone_number` varchar(45) DEFAULT NULL,
                                       `balance` double DEFAULT '0',
                                       `role` varchar(45) DEFAULT NULL,
                                       `active` varchar(10) DEFAULT 'BLOCKED',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY id_UNIQUE (`id`)
)   ENGINE=InnoDB AUTO_INCREMENT=100000
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS tariff (
                                      `id`       bigint NOT NULL AUTO_INCREMENT,
                                      `name_ukr` varchar(100) DEFAULT NULL,
                                      `name_en`  varchar(100) DEFAULT NULL,
                                      `price`    double       DEFAULT NULL,
                                      `service`  varchar(45)  DEFAULT NULL,
                                      PRIMARY KEY (`id`)
)   ENGINE=InnoDB AUTO_INCREMENT=1
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users_has_tariff` (
                                    `users_id`    bigint NOT NULL,
                                    `tariff_id` bigint NOT NULL,
                                    PRIMARY KEY (`users_id`,`tariff_id`),
                                    KEY         `fk_users_has_tariff_tariff1_idx` (`tariff_id`),
                                    KEY         `fk_users_has_tariff_users_idx` (`users_id`),
                                    CONSTRAINT `fk_users_has_tariff_tariff1` FOREIGN KEY (`tariff_id`) REFERENCES `tariff` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT `fk_users_has_tariff_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci;