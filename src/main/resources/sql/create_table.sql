create table books
(
    id bigserial primary key ,
    title varchar(200) not null ,
    author varchar(255) not null ,
    date_added timestamp
);