use job;
show tables;
select * from purchase;
select * from purchase_item;
drop table purchase;
create table purchase(
	id int auto_increment primary key,
	value_total numeric(8.2) not null,
    provider_fk int not null,
    user_fk int,
    date date,
    foreign key (provider_fk) references provider(id)
);
drop table purchase_item;

create table purchase_item(
	purchase_fk int,
    product_fk int,
    amount int,
    value numeric(8.2),
    foreign key (purchase_fk) references purchase(id), 
    foreign key (product_fk) references product(id)
);
insert into stock(product_fk,amount) value(1,0);
insert into stock(product_fk,amount) value(2,0);
drop table stock;
create table stock(
	product_fk int,
    amount int, 
    foreign key (product_fk) references product(id)
);
select * from product;
select * from stock;
use job;
show tables;

create table client(
	id int auto_increment primary key,
    name varchar(50),
    cpf varchar(15) unique,
    sex char(1),
    email varchar(50),
    street varchar(20),
    number_house varchar(4),
    neighborhood varchar(20),
    status int
);
drop table client;
select * from client_telephone;
create table client_telephone(
	client_fk int,
    telephone varchar(20)
);

create table sale(
	id int auto_increment primary key,
    user_fk int,
    client_fk int,
    date date,
    value_total numeric(8,2),
    foreign key (client_fk) references client(id)
);
use job;

show tables;

create table user_telephone(
	user_fk int,
    telephone varchar(20),
    foreign key (user_fk) references user(id)
);
use job;
drop table provider;
select * from provider;
select * from user_telephone;
select * from purchase;
select * from purchase_item;
select * from product;
select * from stock;
select * from sale;
select * from sale_item;
select * from user;
select * from client;
create table sale_item(
	sale_fk int,
    product_fk int,
    amount int,
    value numeric(8.2),
    foreign key (sale_fk) references sale(id),
    foreign key (product_fk) references product(id)
);


create table provider(
	id int auto_increment primary key,
    name varchar(20) unique,
    cnpj varchar(15) unique,
    reason_social varchar(50),
    street varchar(20),
    number_provider varchar(5),
    neighborhood varchar(20),
    status int,
    email varchar(30)
);
drop table provider;

create table provider_telephone(
	provider_fk int,
    telefone varchar(20),
);

create table purchase(
	id int auto_increment primary key,
    valueTotal numeric(8,2),
    provider_fk int,
    user_fk int,
    date date,
    foreign key (provider_fk) references provider(id),
    foreign key (user_fk) references user(id)
);


show tables;

