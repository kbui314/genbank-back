drop table if exists transholder;
drop table if exists accholder;
drop table if exists transaction;
drop table if exists person;
drop table if exists account;

create table person(
	id serial primary key,
	firstname text not null,
	lastname text not null,
	username text not null,
	salt bytea not null,
	hash bytea not null,
	email text not null,
	phonenumber text not null,
	role text not null
);

create table account(
	accountid serial primary key,
	accountnumber text not null,
	balance numeric not null
);

create table accholder(
	accholderid serial primary key,
	accuserid integer references person(id) on delete cascade,
	accnumberid integer references account(accountid) on delete cascade
);

create table transaction(
	transid serial primary key,
	transdate date not null,
	accountfrom text,
	accountto text not null,
	amount numeric not null,
	transtype text not null,
	balance numeric not null,
	message text
);

create table transholder(
	transholderid serial primary key,
	accnumberid integer references account(accountid) on delete cascade,
	transactionid integer references transaction(transid) on delete cascade
);