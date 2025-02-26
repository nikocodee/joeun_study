mydbDROP DATABASE IF EXISTS market_db; -- 만약 market_db가 존재하면 우선 삭제한다.
CREATE DATABASE market_db;

USE market_db;
CREATE TABLE member -- 회원 테이블
( mem_id  		CHAR(8) NOT NULL PRIMARY KEY, -- 사용자 아이디(PK)
  mem_name    	VARCHAR(10) NOT NULL, -- 이름
  mem_number    INT NOT NULL,  -- 인원수
  addr	  		CHAR(2) NOT NULL, -- 지역(경기,서울,경남 식으로 2글자만입력)
  phone1		CHAR(3), -- 연락처의 국번(02, 031, 055 등)
  phone2		CHAR(8), -- 연락처의 나머지 전화번호(하이픈제외)
  height    	SMALLINT,  -- 평균 키
  debut_date	DATE  -- 데뷔 일자
);
CREATE TABLE buy -- 구매 테이블
(  num 		INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 순번(PK)
   mem_id  	CHAR(8) NOT NULL, -- 아이디(FK)
   prod_name 	CHAR(6) NOT NULL, --  제품이름
   group_name 	CHAR(4)  , -- 분류
   price     	INT  NOT NULL, -- 가격
   amount    	SMALLINT  NOT NULL, -- 수량
   FOREIGN KEY (mem_id) REFERENCES member(mem_id)
);

INSERT INTO member VALUES('TWC', '트와이스', 9, '서울', '02', '11111111', 167, '2015.10.19');
INSERT INTO member VALUES('BLK', '블랙핑크', 4, '경남', '055', '22222222', 163, '2016.08.08');
INSERT INTO member VALUES('WMN', '여자친구', 6, '경기', '031', '33333333', 166, '2015.01.15');
INSERT INTO member VALUES('OMY', '오마이걸', 7, '서울', NULL, NULL, 160, '2015.04.21');
INSERT INTO member VALUES('GRL', '소녀시대', 8, '서울', '02', '44444444', 168, '2007.08.02');
INSERT INTO member VALUES('ITZ', '잇지', 5, '경남', NULL, NULL, 167, '2019.02.12');
INSERT INTO member VALUES('RED', '레드벨벳', 4, '경북', '054', '55555555', 161, '2014.08.01');
INSERT INTO member VALUES('APN', '에이핑크', 6, '경기', '031', '77777777', 164, '2011.02.10');
INSERT INTO member VALUES('SPC', '우주소녀', 13, '서울', '02', '88888888', 162, '2016.02.25');
INSERT INTO member VALUES('MMU', '마마무', 4, '전남', '061', '99999999', 165, '2014.06.19');

INSERT INTO buy VALUES(NULL, 'BLK', '지갑', NULL, 30, 2);
INSERT INTO buy VALUES(NULL, 'BLK', '맥북프로', '디지털', 1000, 1);
INSERT INTO buy VALUES(NULL, 'APN', '아이폰', '디지털', 200, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '아이폰', '디지털', 200, 5);
INSERT INTO buy VALUES(NULL, 'BLK', '청바지', '패션', 50, 3);
INSERT INTO buy VALUES(NULL, 'MMU', '에어팟', '디지털', 80, 10);
INSERT INTO buy VALUES(NULL, 'GRL', '혼공SQL', '서적', 15, 5);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 2);
INSERT INTO buy VALUES(NULL, 'APN', '청바지', '패션', 50, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 1);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 4);

SELECT * FROM member;
SELECT * FROM buy;

SELECT m.mem_id
	, m.mem_name
	, (m.mem_number + 10) as 'memAge'
FROM member m
WHERE m.mem_id = 'ITZ'
AND m.memAge = 15

SELECT a.* 
FROM (
	SELECT m.mem_id
		, m.mem_name
		, (m.mem_number + 10) as 'memAge'
	FROM member m
	WHERE m.mem_id = 'ITZ') a
WHERE a.memAge = 15

SELECT a.* 
FROM (
	SELECT m.mem_id
		, m.mem_name
		, (m.mem_number + 10) as 'memAge'
	FROM member m
	WHERE m.mem_id = 'ITZ'
	AND (m.mem_number + 10) > 10
	) a
WHERE a.memAge = 15


SELECT b.price, AVG(amount) 
FROM buy b
GROUP BY price;

SELECT *
	, AVG(price) OVER(PARTITION BY group_name ORDER BY group_name) AS avg
FROM buy

SELECT * , avg(price) FROM buy

-- 조인
SELECT *
FROM member m INNER JOIN buy b
ON m.mem_id = b.mem_id

SELECT m.mem_id
FROM member m INNER JOIN buy b
ON m.mem_id = b.mem_id

SELECT distinct m.mem_id
FROM member m INNER JOIN buy b
ON m.mem_id = b.mem_id


SELECT m.*, b.*
from member m LEFT OUTER JOIN buy b ON m.mem_id = b.mem_id
union
SELECT m.*, b.*
from member m RIGHT OUTER JOIN buy b ON m.mem_id = b.mem_id

SELECT m.*, b.*
from member m CROSS join buy b

SELECT mem_id, mem_name, debut_date
FROM member
WHERE mem_number < 8
ORDER BY mem_id

-- VIEW
CREATE VIEW v_member
AS
SELECT mem_id, mem_name, debut_date
FROM member
WHERE mem_number < 8

SELECT * FROM v_member

-- WITH
WITH t_mem AS (
SELECT mem_id, mem_name, debut_date
FROM member
WHERE mem_number < 7
)
v_member
SELECT t.*, v.* 
FROM t_mem t RIGHT OUTER JOIN v_member v
ON t.mem_id = v.mem_id

SELECT address_detail FROM user







CREATE INDEX idx_address_detail ON user(address_detail)

EXPLAIN SELECT * FROM user WHERE address_detail = '시우이마을'

DROP INDEX idx_address_detail ON user



