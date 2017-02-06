# -- Test table

# --- !Ups

CREATE TABLE test (
  id SERIAL PRIMARY KEY,
  email varchar(255) NOT NULL
);

# --- !Downs

DROP TABLE test;