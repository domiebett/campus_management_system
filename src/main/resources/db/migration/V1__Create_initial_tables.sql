CREATE TABLE courses(
	id			SERIAL		PRIMARY KEY		NOT NULL,
	name		TEXT		NOT NULL		UNIQUE,
	code		VARCHAR(4)	NOT NULL		UNIQUE
);

CREATE TABLE students(
   id           SERIAL      PRIMARY KEY     NOT NULL,
   reg_no       VARCHAR(15) NOT NULL        UNIQUE,
   first_name   TEXT        NOT NULL,
   last_name    TEXT        NOT NULL,
   surname      TEXT,
   course_id	INTEGER		REFERENCES courses(id)
);