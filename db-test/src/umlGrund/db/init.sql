-- erste tabelle zu testzwecken
CREATE TABLE test
(
  name VARCHAR PRIMARY KEY NOT NULL,
  number INT
);

-- tabelle blob erstennem fk aus test
CREATE TABLE blob
(
  name VARCHAR PRIMARY KEY NOT NULL,
  blob BYTEA,
  FOREIGN KEY (name) REFERENCES test (name)
);
