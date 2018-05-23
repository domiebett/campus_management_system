CREATE TABLE students(
   id           SERIAL      PRIMARY KEY     NOT NULL,
   reg_no       VARCHAR(15) NOT NULL        UNIQUE,
   first_name   TEXT        NOT NULL,
   last_name    TEXT        NOT NULL,
   surname      TEXT
);