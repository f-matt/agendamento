-- CREATE DATABASE agendamento

CREATE TABLE espaco_fisico (
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(50)
);


CREATE TABLE eventos (
	id SERIAL PRIMARY KEY,
	id_schedule VARCHAR(50),
	title VARCHAR(50),
	start_date TIMESTAMP,
	end_date TIMESTAMP,
	all_day BOOLEAN,
	description VARCHAR(100)
);

INSERT INTO espaco_fisico (descricao) VALUES
('SALA DE CAPACITAÇÃO PERMANENTE AGHU'),
('SALA DE VIDEOCONFERÊNCIA 1'),
('SALA DE VIDEOCONFERÊNCIA 2'),
('SALA DE VIDEOCONFERÊNCIA 3');




