INSERT INTO public.address (street, house_number, zip_code)
VALUES
    ('Main Street', '12A', '10001'),
    ('Sunset Boulevard', '45B', '90028'),
    ('Nguyen Trai', '120', '700000'),
    ('Orchard Road', '88', '238841');

INSERT INTO public.users (
    first_name,
    last_name,
    email,
    username,
    password,
    address_id
)
VALUES
    (
        'John',
        'Doe',
        'john.doe@example.com',
        'johndoe',
        '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5GJZ8n1bq7xO1X8FQ6V5zPp6KQ9cW',
        1
    ),
    (
        'Jane',
        'Smith',
        'jane.smith@example.com',
        'janesmith',
        '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5GJZ8n1bq7xO1X8FQ6V5zPp6KQ9cW',
        2
    ),
    (
        'Minh',
        'Nguyen',
        'minh.nguyen@example.com',
        'minhng',
        '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5GJZ8n1bq7xO1X8FQ6V5zPp6KQ9cW',
        3
    ),
    (
        'Alice',
        'Tan',
        'alice.tan@example.com',
        'alicetan',
        '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5GJZ8n1bq7xO1X8FQ6V5zPp6KQ9cW',
        4
    );
