INSERT INTO public.category (id, name, description)
VALUES
    (nextval('category_seq'), 'Beverages', 'Soft drinks, coffee, tea, juices, and bottled drinks'),
    (nextval('category_seq'), 'Snacks', 'Chips, nuts, crackers, and quick bites'),
    (nextval('category_seq'), 'Bakery', 'Bread, cakes, pastries, and baked goods'),
    (nextval('category_seq'), 'Frozen Foods', 'Frozen meals, ice cream, and frozen snacks'),
    (nextval('category_seq'), 'Dairy', 'Milk, cheese, yogurt, and dairy products');

INSERT INTO public.product (id, name, description, seller_id, quantity, price, category_id)
VALUES
-- Beverages (category_id = 1)
(nextval('product_seq'), 'Coca-Cola Can', '330ml soft drink can', 1, 500, 1.25, 1),
(nextval('product_seq'), 'Orange Juice', '1L fresh orange juice', 4, 200, 2.99, 1),
(nextval('product_seq'), 'Iced Coffee', 'Cold brew coffee bottle', 5, 150, 3.49, 1),

-- Snacks (category_id = 51)
(nextval('product_seq'), 'Potato Chips', 'Salted potato chips 150g', 3, 300, 2.10, 51),
(nextval('product_seq'), 'Mixed Nuts', 'Roasted mixed nuts 200g', 2, 180, 4.75, 51),

-- Bakery (category_id = 101)
(nextval('product_seq'), 'Baguette', 'Freshly baked French baguette', 2, 100, 1.80, 101),
(nextval('product_seq'), 'Chocolate Croissant', 'Butter croissant with chocolate filling', 1, 120, 2.50, 101),

-- Frozen Foods (category_id = 151)
(nextval('product_seq'), 'Frozen Pizza', 'Cheese frozen pizza 12 inch', 5, 90, 5.99, 151),
(nextval('product_seq'), 'Vanilla Ice Cream', '1L vanilla ice cream tub', 4, 110, 4.50, 151),

-- Dairy (category_id = 201)
(nextval('product_seq'), 'Whole Milk', '1L whole milk', 3, 400, 1.20, 201),
(nextval('product_seq'), 'Cheddar Cheese', '200g cheddar cheese block', 1, 160, 3.95, 201);
