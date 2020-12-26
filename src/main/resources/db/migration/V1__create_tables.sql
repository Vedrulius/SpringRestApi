CREATE TABLE regions (
id SERIAL,
name VARCHAR(40),
PRIMARY KEY (id)
);

CREATE TABLE users (
id SERIAL,
user_name VARCHAR(40),
password VARCHAR(255),
role VARCHAR(10),
status VARCHAR(10),
PRIMARY KEY (id)
);

CREATE TABLE writers (
id SERIAL,
first_name VARCHAR(40),
last_name VARCHAR(40),
region_id INT NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE posts (
id SERIAL PRIMARY KEY,
writer_id INT NOT NULL,
content TEXT,
created TIMESTAMP NOT NULL DEFAULT now(),
updated TIMESTAMP NOT NULL DEFAULT now(),
status VARCHAR(15) NOT NULL,
CONSTRAINT fk_writers FOREIGN KEY (writer_id)
REFERENCES writers(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);