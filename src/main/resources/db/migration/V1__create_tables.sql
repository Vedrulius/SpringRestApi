CREATE TABLE regions (
id SERIAL,
name VARCHAR(40) NOT NULL UNIQUE,
PRIMARY KEY (id)
);

CREATE TABLE users (
id SERIAL,
user_name VARCHAR(40) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(10) DEFAULT 'USER',
status VARCHAR(25) DEFAULT 'CONFIRMATION_REQUIRED',
PRIMARY KEY (id)
);

CREATE TABLE writers (
user_id SERIAL,
first_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
region_id INT NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE posts (
id SERIAL PRIMARY KEY,
writer_id INT NOT NULL,
content TEXT,
created TIMESTAMP DEFAULT now(),
updated TIMESTAMP DEFAULT now(),
status VARCHAR(15) DEFAULT 'UNDER_REVIEW',
CONSTRAINT fk_writers FOREIGN KEY (writer_id)
REFERENCES writers(user_id)
ON DELETE CASCADE
ON UPDATE CASCADE
);