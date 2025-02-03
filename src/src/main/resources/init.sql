CREATE SCHEMA IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS student;
CREATE TABLE students (id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(80),
                       surname VARCHAR(80),
                        course_name VARCHAR(100)
                      );

INSERT INTO  students(name, surname, course_name) VALUES ('Raymond', 'Smith', 'Cs50')
