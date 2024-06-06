CREATE TABLE usuarios(
    id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(100) NOT NULL unique,
    login VARCHAR(100) NOT NULL unique,
    senha VARCHAR(50) NOT NULL
);