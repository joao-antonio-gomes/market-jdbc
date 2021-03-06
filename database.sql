create table if not exists customer
(
    id       serial primary key,
    name     varchar(255) not null,
    cellphone varchar(255) not null,
    cpf      varchar(11)  not null unique
);

create table if not exists product
(
    id    serial primary key,
    name  varchar(255)   not null,
    price numeric(10, 2) not null
);

create table if not exists "order"
(
    id          serial primary key,
    customer_id  integer not null references customer (id),
    product_id   integer not null references product (id),
    order_date date    not null
);

insert into customer (name, cellphone, cpf) values ('João', '11999999999', '12345678901');
insert into product (name, price) values ('Banana', 2.50);
insert into "order" (customer_id, product_id, order_date) values (1, 1, '2020-01-01');