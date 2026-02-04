INSERT INTO public.category (name, description)
VALUES
('Beverages', 'Soft drinks, coffee, tea, juices, and bottled drinks'),
('Snacks', 'Chips, nuts, crackers, and quick bites'),
('Bakery', 'Bread, cakes, pastries, and baked goods'),
('Frozen Foods', 'Frozen meals, ice cream, and frozen snacks'),
('Dairy', 'Milk, cheese, yogurt, and dairy products');

INSERT INTO public.product (name, description, seller_id, quantity, price, category_id)
VALUES
-- Beverages
('Coca-Cola Can', '330ml soft drink can', 1, 500, 1.25, 1),
('Orange Juice', '1L fresh orange juice', 4, 200, 2.99, 1),
('Iced Coffee', 'Cold brew coffee bottle', 5, 150, 3.49, 1),

('Potato Chips', 'Salted potato chips 150g', 3, 300, 2.10, 3),
('Mixed Nuts', 'Roasted mixed nuts 200g', 2, 180, 4.75, 3),

-- Bakery
('Baguette', 'Freshly baked French baguette', 2, 100, 1.80, 2),
('Chocolate Croissant', 'Butter croissant with chocolate filling', 1, 120, 2.50, 2),

-- Frozen Foods
('Frozen Pizza', 'Cheese frozen pizza 12 inch', 5, 90, 5.99, 4),
('Vanilla Ice Cream', '1L vanilla ice cream tub', 4, 110, 4.50, 4),

-- Dairy
('Whole Milk', '1L whole milk', 3, 400, 1.20, 5),
('Cheddar Cheese', '200g cheddar cheese block', 1, 160, 3.95, 5);
