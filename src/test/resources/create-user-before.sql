DELETE FROM provider_db_test.user;
DELETE FROM provider_db_test.user_has_tariff;
ALTER TABLE provider_db_test.user_has_tariff AUTO_INCREMENT = 1;
ALTER TABLE provider_db_test.user AUTO_INCREMENT = 100000;

INSERT INTO provider_db_test.user(full_name_en, full_name_ukr, password, email, address, phone_number, role, status)
VALUES ('Admin', 'Адмін', '$2a$12$.q6pTemEQEp69bCnUhZIjO/v.QzpS9AwkyVqMFqYTgpkfeE/xwXBS', 'admin@gmail.com',
        'someAddress', '+380448652248', 'ADMIN', 'ACTIVE'),
       ('John Anderson', 'Джон Андерсон', '$2a$12$vFT36g9m8DhiX.lJJ01sfOB8UbvWYJ6926jqCRoD9qaeNPraA36QG',
        'john.a@gmail.com', 'м. Фастів, вул. Симиренка 4, кв. 16', '+448855214586', 'CLIENT', 'ACTIVE');