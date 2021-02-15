INSERT INTO users (full_name_en,
                   full_name_ukr,
                   password, email,
                   address,
                   phone_number,
                   user_role)
            VALUES ('Max Peterson',
                    'Максим Петренко',
                    '123321',
                    'max1990@gmail.com',
                    'м.Київ, вул. УКраїнмська 3',
                    '+380506678495',
                    'client');

INSERT INTO tariff (tariff_name_ukr,
                    tariff_name_en,
                    tariff_price,
                    tariff_service)
            VALUES ('Базовий',
                    'Basic',
                    100.00,
                    'internet');
