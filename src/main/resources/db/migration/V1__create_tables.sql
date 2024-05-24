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
    -- outros campos específicos
    CONSTRAINT chk_tipo CHECK (tipo IN ('cliente', 'fornecedor', 'prestador'))
);

CREATE TABLE propostas (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- pode ser 'cliente', 'fornecedor' ou 'prestador'
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    entidade_id bigint not null
    -- outros campos específicos
    CONSTRAINT chk_tipo CHECK (tipo IN ('cliente', 'fornecedor', 'prestador')),
    FOREIGN KEY (entidade_id) REFERENCES entidades (id)
);

CREATE TABLE transacoes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- pode ser 'cliente', 'fornecedor' ou 'prestador'
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    entidade_id bigint not null
    -- outros campos específicos
    CONSTRAINT chk_tipo CHECK (tipo IN ('cliente', 'fornecedor', 'prestador'))
);