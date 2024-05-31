create table usuarios(
    id SERIAL PRIMARY KEY,
	nome varchar(255) not null,
    login varchar(100) not null unique,
    senha varchar(255) not null
);

CREATE TABLE entidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL -- pode ser 'cliente', 'fornecedor' ou 'prestador'

    CONSTRAINT chk_tipo CHECK (tipo IN ('CLIENTE', 'FORNECEDOR', 'PRESTADOR'))
);

CREATE TABLE propostas (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    entidade_id bigint not null,
    tipo VARCHAR(50) NOT NULL, -- pode ser 'residencial' ou 'comercial'
    FOREIGN KEY (entidade_id) REFERENCES entidades (id)
);

CREATE TABLE transacoes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    entidade_id bigint not null,
    FOREIGN KEY (entidade_id) REFERENCES entidades (id)
);