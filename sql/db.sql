DROP DATABASE warsztat2krajee04;

CREATE DATABASE warsztat2krajee04
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_Ci;

USE warsztat2krajee04;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT  NOT NULL,
    username VARCHAR(255),
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
#     user_group_id INT,
    PRIMARY KEY (ID)
#     FOREIGN KEY (user_group_id)

);
create table user_group
(
    id   int auto_increment,
    name varchar(255) null,
    constraint user_group_pk
        primary key (id)
);
create table exercise
(
    id          int auto_increment,
    title       varchar(255) null,
    description text         null,
    constraint exercise_pk
        primary key (id)
);
create table exercise
(
    id          int auto_increment
        primary key,
    title       varchar(255) null,
    description text         null
);

