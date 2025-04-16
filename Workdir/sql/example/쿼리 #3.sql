-- 테스트
CREATE table students(
	id INTEGER PRIMARY KEY,
	NAME TEXT NOT NULL,
	age INTEGER,
	major text
);

INSERT INTO students (id, name, age, major)
VALUES (1, 'Alice', 21, 'Computer Science'),
       (2, 'Bob', 22, 'Mathematics'),
       (3, 'Charlie', 20, 'Physics');
       

SELECT * FROM STUDENTS

SELECT NAME,major FROM students

UPDATE students 
SET age = 23
WHERE id = 2

SELECT * FROM students
WHERE major = 'Mathematics'
and age >= 22

SELECT * FROM students
WHERE NOT major = 'Physics'

INSERT INTO students (id, NAME, major) VALUES (4,'a','fff')

SELECT * FROM students
WHERE age IS NULL

SELECT COUNT(*) as total_students FROM students

SELECT MIN(age) AS young, MAX(age) AS OLD FROM students

SELECT SUM(age) AS total , AVG(age) AS average FROM students

SELECT major, COUNT(*) as COUNT
FROM students
GROUP BY major

UPDATE students
SET major = 'Mathematics'
WHERE id = 4

SELECT major, 



