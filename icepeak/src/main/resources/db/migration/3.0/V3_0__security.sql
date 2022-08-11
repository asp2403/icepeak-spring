create table d_role (
    id_role bigserial not null primary key,
    role_name varchar(255) not null
);

create unique index uq_d_role on d_role(role_name);

create table "user" (
    id_user bigserial not null primary key,
    id_role bigint not null,
    name varchar(255) not null,
    surname varchar(255),
    email varchar(100) not null,
    pwd_hash varchar(255) not null,
    auth_token varchar(255),
    is_active boolean not null default true,
    constraint fk_user_d_role foreign key(id_role) references d_role(id_role)
);

create unique index uq_user_email on "user"(email);

create index ix_user_id_role on "user"(id_role);

create table customer (
    id_user bigint not null primary key,
    discount integer,
    constraint fk_customer_user foreign key (id_user) references "user"(id_user) on delete cascade
)


