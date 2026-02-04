create table if not exists category
(
    id integer not null primary key,
    description varchar(255),
    name varchar(255)
);

create table if not exists product
(
    id integer not null primary key,
    description varchar(255),
    name varchar(255),
    quantity double precision not null,
    price numeric(38, 2),   --  allow up to 38 digits, 2 decimal places
    seller_id integer not null,
-- mapping/ add relationship between 2 tables
    category_id integer
        constraint fk1_random_text references category
);

-- default increase by 50, if need other value -> have to customize at Entity lv
create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;