
create table "order" (
    id_order bigserial not null primary key,
    order_date timestamp not null,
    id_customer bigint not null,
    id_manager bigint not null,
    state int not null,
    ready_date timestamp,
    sale_date timestamp,
    constraint fk_order_customer foreign key (id_customer) references customer(id_user) on delete cascade,
    constraint fk_order_manager foreign key (id_manager) references "user"(id_user) on delete cascade
);

create index ix_order_customer on "order"(id_customer);
create index ix_order_manager on "order"(id_manager);
create index ix_order_state on "order"(state);

create table product_order (
    id_product_order bigserial not null primary key,
    id_product bigint not null,
    id_order bigint not null,
    sale_price int not null,
    qty int not null,
    constraint fk_product_order_product foreign key (id_product) references product(id_product) on delete cascade,
    constraint fk_product_order_order foreign key (id_order) references "order"(id_order) on delete cascade
);

create unique index uq_product_order on product_order(id_product, id_order);