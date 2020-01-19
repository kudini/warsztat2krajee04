DROP DATABASE warsztat2krajeew04;

CREATE DATABASE warsztat2krajeew04
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_Ci;

USE warsztat2krajeew04;

CREATE TABLE users(
    id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
#     user_group_id INT,
    PRIMARY KEY (ID)
#     FOREIGN KEY (user_group_id)

);