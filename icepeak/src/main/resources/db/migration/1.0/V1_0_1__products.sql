
create table ski(
    id_product bigint not null primary key,
    height smallint not null,
    constraint fk_ski_product foreign key(id_product) references product(id_product) on delete cascade
);

create table boots(
    id_product bigint not null primary key,
    size real not null,
    constraint fk_boots_product foreign key(id_product) references product(id_product) on delete cascade
);

