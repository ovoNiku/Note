create table todo (
id int not null primary key auto_increment,
status int(10),
content varchar(1000),
create_time date,
update_time date
);
create table user (
id int not null primary key auto_increment,
name varchar(1000),
password varchar(1000),
create_time date,
update_time date
);
