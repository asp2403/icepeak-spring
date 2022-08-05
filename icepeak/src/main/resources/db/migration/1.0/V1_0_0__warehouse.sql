
create table vendor(
    id_vendor bigserial not null primary key,
    vendor varchar(255) not null
);

create table d_gender(
    id_gender integer not null primary key,
    gender varchar(255) not null
);

create table d_age(
    id_age integer not null primary key,
    age varchar(255) not null
);

create table model(
    id_model bigserial not null primary key,
    model varchar(255) not null,
    id_vendor bigint not null,
    description text,
    id_gender integer not null,
    id_age integer not null,
    price integer not null,
    image_small bytea,
    image_large bytea,
    constraint fk_model_vendor foreign key(id_vendor) references vendor(id_vendor),
    constraint fk_model_d_gender foreign key (id_gender) references d_gender(id_gender),
    constraint fk_model_d_age foreign key (id_age) references d_age(id_age)
);

create index ix_model_id_vendor on model(id_vendor);
create index ix_model_id_gender on model(id_gender);
create index ix_model_id_age on model(id_age);

create table product(
    id_product serial primary key,
    id_model bigint not null,
    qty_available integer not null,
    qty_reserved integer not null,
    constraint fk_product_model foreign key(id_model) references model(id_model) on delete cascade
);

create index ix_product_id_model on product(id_model);