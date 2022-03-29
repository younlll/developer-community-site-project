drop table users;
create table users (
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

drop table category;
create table category (
    category_id int not null,
    category_name varchar(30) not null,
    use_yn char(1) not null,
    super_category_id int,
    link_page_url varchar(30),
    reg_dttm timestamp default current_timestamp,
    upd_dttm timestamp default current_timestamp,
    primary key (category_id)
);

drop table question_by_category;
create table question_by_category (
    question_id int not null,
    category_id int not null,
    question_title varchar(100) not null,
    user_id int not null,
    enabled_yn char(1),
    views int,
    description varchar(5000),
    reg_dttm timestamp default current_timestamp,
    upd_dttm timestamp default current_timestamp,
    primary key (question_id, category_id)
);