WITH temp AS(
	SELECT NAME,phone,address,user_class
	FROM user
	WHERE NAME LIKE '김%'
	)
, temp1 AS (
SELECT * 
FROM temp 
WHERE address LIKE '세종%'
)
, temp2 AS(
SELECT * 
FROM temp
WHERE address LIKE '강원%'
)
SELECT * 
FROM temp1 INNER JOIN temp2
WHERE temp1.user_class = temp2.user_class
AND temp1.user_class = 'VIP'

--

SELECT * FROM sejong_kim_user se
WHERE se.user_class = 'VIP'

CREATE VIEW v_sejong_kim_user AS(
	SELECT NAME,phone,address,user_class
	FROM user
	WHERE NAME LIKE '김%'
	AND address LIKE '세종%'
);

SELECT * FROM v_sejong_kim_user

--


WITH aggre AS(
	SELECT user_class, COUNT(address)
	FROM user
	GROUP BY user_class
	ORDER BY user_class
)
SELECT a.*, u.* 
FROM aggre a INNER JOIN user u
ON a.user_class = u.user_class


SELECT COUNT(address) OVER(PARTITION BY user_class), u.*
FROM user u

----

CREATE VIEW v_user_secure AS(
SELECT case when user_class = 'VVIP' then 1
				when user_class = 'VIP' then 2
				when user_class = 'Family' then 3
				when user_class = '일반' then 4
		END 'secure', u.*
FROM user u
)

SELECT * FROM v_user_secure

----


SELECT DENSE_RANK() OVER(PARTITION BY user_class ORDER BY v.created_at) 'Rank'
		,v.name, v.user_class, v.created_at
FROM v_user_secure v


-----


SELECT LAG(id,1) OVER(ORDER BY id) 'Previous', u.*
FROM user u 





SELECT LEAD(id,2) OVER(ORDER BY id) 'Next', u.*
FROM user u 






WITH piv_user_class AS(
SELECT id, NAME, user_class		
	  , case when user_class = 'VVIP' then 1
	  			ELSE 0
	  	 END AS  'VVIP'
	  , case when user_class = 'VIP' then 1
	  			ELSE 0
	  	 END AS  'VIP'
	  , case when user_class = 'Family' then 1
	  			ELSE 0
	  	 END AS  'Family'
	   , case when user_class = '일반' then 1
	  			ELSE 0
	  	 END AS  '일반'
FROM user
)
SELECT id, NAME
		, SUM(VVIP) AS vvip
FROM piv_user_class
GROUP BY id, NAME

SELECT id, NAME
		, SUM(VVIP) OVER() AS vvip_sum
		, SUM(vip) OVER() AS vip_sum
		, SUM(Family) OVER() AS family_sum
		, SUM(일반) OVER() AS general_sum
FROM piv_user_class
				 

CREATE TABLE employees (
    id INTEGER PRIMARY KEY,
    name TEXT,
    manager_id INTEGER,  -- 상위 관리자 ID (NULL이면 최고 관리자)
    FOREIGN KEY (manager_id) REFERENCES employees(id)
);

INSERT INTO employees (id, name, manager_id)
VALUES (1, 'CEO', NULL),
       (2, 'Manager 1', 1),
       (3, 'Manager 2', 1),
       (4, 'Employee 1', 2),
       (5, 'Employee 2', 2),
       (6, 'Employee 3', 3),
       (7, 'Employee 4', 3);
       
SELECT * FROM employees

WITH RECURSIVE employee_hierarchy AS (
    -- Anchor member: 최고 관리자 (최상위 레벨)
    SELECT id, name, manager_id, 1 AS level
    FROM employees
    WHERE manager_id IS NULL

    UNION ALL

    -- Recursive member: 상위 관리자의 직속 부하를 재귀적으로 찾음
    SELECT e.id, e.name, e.manager_id, eh.level + 1
    FROM employees e
    JOIN employee_hierarchy eh ON e.manager_id = eh.id
)
SELECT * FROM employee_hierarchy;


WITH RECURSIVE employee_hierarchy AS (
    SELECT id, name, manager_id, 1 AS level, CAST(name AS CHAR(100)) AS path
    FROM employees
    WHERE manager_id IS NULL

    UNION ALL

    SELECT e.id, e.name, e.manager_id, eh.level + 1,
           CONCAT(eh.path, ' -> ', e.name) AS path
    FROM employees e
    JOIN employee_hierarchy eh ON e.manager_id = eh.id
)
SELECT * FROM employee_hierarchy
ORDER BY PATH;

-----

CREATE TABLE sales (
    year INTEGER,
    month INTEGER,
    region TEXT,
    product TEXT,
    amount INTEGER
);

INSERT INTO sales (year, month, region, product, amount)
VALUES (2023, 1, 'North', 'Product A', 1000),
       (2023, 1, 'North', 'Product B', 1500),
       (2023, 1, 'South', 'Product A', 1200),
       (2023, 2, 'North', 'Product A', 1100),
       (2023, 2, 'South', 'Product B', 1600),
       (2022, 1, 'North', 'Product A', 1300),
       (2022, 1, 'South', 'Product B', 1700),
       (2022, 2, 'North', 'Product A', 900),
       (2022, 2, 'South', 'Product A', 800);
       
SELECT * FROM sales

SELECT year, month, SUM(amount) AS total_sales
FROM sales
-- GROUP BY ROLLUP (year, MONTH)
GROUP BY YEAR, MONTH WITH ROLLUP

---

SELECT year, month, region, SUM(amount) AS total_sales
FROM sales
GROUP BY CUBE (year, month, region);


SELECT year, region, SUM(amount) AS total_sales
FROM sales
GROUP BY GROUPING SETS ((year), (region));


SELECT year, month, SUM(amount) AS total_sales,
       GROUPING(year) AS grouping_year,
       GROUPING(month) AS grouping_month
FROM sales
GROUP BY ROLLUP (year, MONTH);

