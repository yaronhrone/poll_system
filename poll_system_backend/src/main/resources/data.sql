CREATE TABLE question(
id INT AUTO_INCREMENT NOT NULL,
question VARCHAR(1000) UNIQUE NOT NULL,
first_option VARCHAR(1000) NOT NULL,
second_option VARCHAR(1000) NOT NULL,
third_option VARCHAR(1000) NOT NULL,
fourth_option VARCHAR(1000) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE answer_question(
id  INT  AUTO_INCREMENT ,
id_question INT NOT NULL ,
answer_number INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY(id_question) REFERENCES question(id),
PRIMARY KEY(id)
);
INSERT INTO question (question, first_option, second_option, third_option, fourth_option) VALUES
('What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome'),
('Which planet is known as the Red Planet?', 'Earth', 'Mars', 'Jupiter', 'Venus'),
('Who wrote "Romeo and Juliet"?', 'Charles Dickens', 'Mark Twain', 'William Shakespeare', 'Homer'),
('What is the chemical symbol for water?', 'CO2', 'H2O', 'O2', 'NaCl');