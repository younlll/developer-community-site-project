drop table user;
create table user (
    user_id int not null,
    email varchar(30) not null,
    name varchar(50) not null,
    password varchar(15) not null,
    nick_name varchar(20) not null,
    github varchar(30),
    blog varchar(30),
    reg_dttm timestamp default current_timestamp,
    upd_dttm timestamp default current_timestamp,
    primary key(user_id, email)
);