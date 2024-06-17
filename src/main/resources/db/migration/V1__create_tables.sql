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

CREATE TABLE propostas (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    id_entidade bigint not null,
    tipo INTEGER NOT NULL CHECK (tipo >= 0),
    status INTEGER NOT NULL CHECK (status >= 0),
    FOREIGN KEY (id_entidade) REFERENCES entidades (id)
);

CREATE TABLE movimentacoes (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_entidade BIGINT NOT NULL,
    id_proposta BIGINT NOT NULL,
    tipo INTEGER NOT NULL CHECK (tipo >= 0),
    FOREIGN KEY (id_proposta) REFERENCES propostas (id)
);

CREATE TABLE saldos (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_proposta BIGINT NOT NULL,
    FOREIGN KEY (id_proposta) REFERENCES propostas (id)
);