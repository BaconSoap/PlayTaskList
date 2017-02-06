# -- Test table

# --- !Ups

DROP TABLE test;

# --- !Downs

CREATE TABLE test (
  id SERIAL PRIMARY KEY,
  email varchar(255) NOT NULL
);
