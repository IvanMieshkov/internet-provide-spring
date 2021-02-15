DELETE FROM provider_db_test.tariff;
ALTER TABLE provider_db_test.tariff AUTO_INCREMENT = 1;

INSERT INTO provider_db_test.tariff(name_en, name_ukr, price, service)
VALUES ('Basic', 'Базовий', '100.00', 'internet'),
       ('Fast', 'Швидкий', '150.00', 'internet'),
       ('Sport', 'Спорт', '50.00', 'tv'),
       ('UNLIM', 'Безлімітний', '120.00', 'telephony')