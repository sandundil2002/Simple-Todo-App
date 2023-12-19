CREATE DATABASE IF NOT EXISTS todo;

USE todo;

CREATE TABLE user(
    email VARCHAR(35) PRIMARY KEY,
    name VARCHAR(155) NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE task(
    task_id int PRIMARY KEY,
    email VARCHAR(155) NOT NULL,
    description TEXT NOT NULL,
    due_date DATE NOT NULL,
    isCompleted TINYINT NOT NULL,
    FOREIGN KEY(email) REFERENCES user(email) ON DELETE CASCADE ON UPDATE CASCADE
);