# -- Add to tasks

# --- !Ups

INSERT INTO tasks (text) VALUES ('arv: test task 1'), ('arv: test task 2'), ('arv: test task 3');

# --- !Downs

DELETE FROM tasks where text in ('arv: test task 1', 'arv: test task 2', 'arv: test task 3');