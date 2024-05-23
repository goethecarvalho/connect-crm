create table usuarios(
    id SERIAL PRIMARY KEY,
	nome varchar(255) not null,
    login varchar(100) not null unique,
    senha varchar(255) not null
);
