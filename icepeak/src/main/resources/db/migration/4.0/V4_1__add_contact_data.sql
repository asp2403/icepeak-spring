
alter table "order" add column name varchar(255) not null;
alter table "order" add column surname varchar(255);
alter table "order" add column email varchar(100) not null;
alter table "order" add column phone varchar(50) not null;

create index ix_order_email on "order"(email);

alter table "user" add column phone varchar(50) not null;