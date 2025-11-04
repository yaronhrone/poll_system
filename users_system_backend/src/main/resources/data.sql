CREATE TABLE users (
id INT  AUTO_INCREMENT NOT NULL ,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
age INT NOT NULL,
address VARCHAR(50),
username VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(550) NOT NULL,
role VARCHAR(50) DEFAULT 'USER',
joining_date DATE NOT NULL DEFAULT CURRENT_DATE,
PRIMARY KEY(id)
);

INSERT INTO users (first_name, last_name, email, age, address, username, password, role, joining_date) VALUES
('yaron', 'haroni','yaronhrone@gmail.com',35,'israel','yaron','$2a$10$bsK1s9DDupJJV0W6B.BrH.IitOJPFUsnyq.K2/iIH3sPL/lAkYvde','ADMIN','2022-10-04');