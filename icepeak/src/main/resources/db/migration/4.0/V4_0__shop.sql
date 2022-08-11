create table d_order_state (
    id_state serial not null primary key,
    state varchar(255) not null unique
);

insert into d_order_state(id_state, state) values (1, 'Формируется');
insert into d_order_state(id_state, state) values (2, 'Готов к выдаче');
insert into d_order_state(id_state, state) values (3, 'Выполнен');
insert into d_order_state(id_state, state) values (4, 'Отменен');

create table "order" (
    id_order bigserial not null primary key,
    order_date timestamp not null,
    id_customer bigint not null,
    id_manager bigint not null,
    id_state int not null,
    ready_date timestamp,
    sale_date timestamp,
    constraint fk_order_customer foreign key (id_customer) references customer(id_user) on delete cascade,
    constraint fk_order_manager foreign key (id_manager) references "user"(id_user) on delete cascade,
    constraint fk_state_d_order_state foreign key (id_state) references d_order_state(id_state) on delete cascade
);

create index ix_order_customer on "order"(id_customer);
create index ix_order_manager on "order"(id_manager);
create index ix_order_state on "order"(id_state);

create table product_order (
    id_product bigint not null,
    id_order bigint not null,
    sale_price int not null,
    qty int not null,
    constraint fk_product_order_product foreign key (id_product) references product(id_product) on delete cascade,
    constraint fk_product_order_order foreign key (id_order) references "order"(id_order) on delete cascade
);

create unique index uq_product_order on product_order(id_product, id_order);