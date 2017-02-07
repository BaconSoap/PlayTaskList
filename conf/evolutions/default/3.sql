# -- Tasks

# --- !Ups

CREATE TABLE tasks (
  id SERIAL PRIMARY KEY NOT NULL,
  text VARCHAR(1000)
);
# --- !Downs

DROP TABLE tasks;