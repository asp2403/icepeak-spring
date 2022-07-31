alter table product drop column id_product_type cascade;

alter table model add column id_product_type bigint;

alter table model add constraint fk_model_d_product_type foreign key (id_product_type) references d_product_type(id_product_type);

create index ix_model_product_type on model(id_product_type);