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

CREATE TABLE lecturers(
	id			SERIAL		PRIMARY KEY		NOT NULL,
	staff_id	VARCHAR(20)	NOT NULL		UNIQUE,
	first_name	TEXT		NOT NULL,
	last_name	TEXT		NOT NULL,
	surname		TEXT
);

CREATE TABLE units(
	id			SERIAL		PRIMARY KEY		NOT NULL,
	name		TEXT		NOT NULL		UNIQUE,
	code		VARCHAR(10)	NOT NULL		UNIQUE,
	course_id	INTEGER		REFERENCES courses(id),
	lecturer_id	INTEGER		REFERENCES lecturers(id)
);

CREATE TABLE results(
	id			SERIAL			PRIMARY KEY		NOT NULL,
	course_id	INTEGER			REFERENCES courses(id),
	unit_id		INTEGER 		REFERENCES units(id),
	student_id	INTEGER			REFERENCES students(id),
	score		DECIMAL (3,2)	NOT NULL,
	year_of_study	INTEGER		NOT NULL,
	semester	INTEGER			NOT NULL
);