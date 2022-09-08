USE QUESTBANK;

CREATE OR REPLACE TABLE TB_CATEGORY(
	CATEGORY_NO		INT			PRIMARY KEY AUTO_INCREMENT,
	CATEGORY_NAME	VARCHAR(20)
)

CREATE OR REPLACE TABLE TB_USER(
	USER_ID			VARCHAR(20),
	USER_NICKNAME	VARCHAR(20),
	USER_PWD			CHAR(128),
	USER_ROLE		VARCHAR(10),
	USER_EXP			INT,
	REG_TIME			DATETIME,
	USE_F				CHAR(1)
)


CREATE OR REPLACE TABLE TB_COMMENT(
	ID		INT		PRIMARY KEY	AUTO_INCREMENT,
	USER_ID		VARCHAR(20)	,
	ARTICLE_NO	INT		NOT NULL,
	CONTENT		TEXT		NOT NULL,
	REG_DATE	DATETIME	NOT NULL,
	USE_F		CHAR(1)
);

CREATE OR REPLACE TABLE TB_LOG(
	USER_ID		VARCHAR(20)	NOT NULL,
	COMMAND	VARCHAR(20)	NOT NULL,
	TARGET_ID	INT,
	TIME		DATETIME	NOT NULL
);
