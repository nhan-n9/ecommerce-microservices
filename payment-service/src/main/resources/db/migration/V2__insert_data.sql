INSERT INTO public.orders (
    customer_id,
    created_date,
    payment_method,
    status,
    total_price
)
VALUES
    (1, '2025-01-10 10:15:00', 'MASTER_CARD', 'complete', 18.45),
    (2, '2025-01-11 14:30:00', 'PAYPAL', 'pending', 32.90),
    (3, '2025-01-12 09:05:00', 'VISA', 'pending', 12.75),
    (1, '2025-01-13 19:40:00', 'MASTER_CARD', 'withdraw', 45.20);


INSERT INTO public.order_items (
    quantity,
    prod_id,
    order_id
)
VALUES
-- Order 1 (customer 1)
(2, 1, 1),   -- 2 Coca-Cola
(1, 4, 1),   -- 1 Chips

-- Order 2 (customer 2)
(3, 2, 2),   -- 3 Orange Juice
(2, 10, 2),  -- 2 Milk
(1, 6, 2),   -- 1 Baguette

-- Order 3 (customer 3)
(1, 3, 3),   -- 1 Iced Coffee
(2, 7, 3),   -- 2 Croissants

-- Order 4 (customer 1)
(1, 8, 4),   -- 1 Frozen Pizza
(2, 9, 4);   -- 2 Ice Cream