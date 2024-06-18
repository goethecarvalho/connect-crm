CREATE TABLE usuarios(
    id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL unique,
    senha VARCHAR(50) NOT NULL
);

CREATE TABLE entidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo INTEGER NOT NULL CHECK (tipo >= 0)
);