DROP DATABASE warsztat2krajee04;

CREATE DATABASE warsztat2krajee04
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_Ci;

USE warsztat2krajee04;

CREATE TABLE users(
                     id INT AUTO_INCREMENT,
                     username VARCHAR(255),
                     email VARCHAR(255) UNIQUE NOT NULL ,
                     password VARCHAR(245) NOT NULL ,
                     PRIMARY KEY (id)
);
CREATE TABLE exercises(
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         title VARCHAR(255),
                         description TEXT
);

CREATE TABLE users_groups(
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(255)

);
CREATE TABLE solutions(
                          id INT(11) PRIMARY KEY AUTO_INCREMENT,
                          created DATETIME ,
                          updated DATETIME ,
                          description TEXT ,
                          exercise_id INT,
                          users_id INT,
                          FOREIGN KEY (exercise_id) REFERENCES exercise(id),
                          FOREIGN KEY (users_id) REFERENCES  user(id)
);

