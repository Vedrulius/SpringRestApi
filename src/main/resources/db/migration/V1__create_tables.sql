CREATE TABLE regions (
id SERIAL,
name VARCHAR(40) NOT NULL UNIQUE,
PRIMARY KEY (id)
);

CREATE TABLE users (
id SERIAL,
user_name VARCHAR(40) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
phone_number VARCHAR(20) NOT NULL UNIQUE,
role VARCHAR(10) DEFAULT 'USER',
status VARCHAR(25) DEFAULT 'CONFIRMATION_REQUIRED',
PRIMARY KEY (id)
);

CREATE TABLE codes (
user_id SERIAL PRIMARY KEY,
code VARCHAR(5) NOT NULL,
is_confirmed BOOLEAN NOT NULL DEFAULT FALSE,
created TIMESTAMP DEFAULT now(),
CONSTRAINT fk_users FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE
ON UPDATE CASCADE
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