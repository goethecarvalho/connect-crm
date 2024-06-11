CREATE TABLE propostas (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    id_entidade bigint not null,
    tipo INTEGER NOT NULL, -- pode ser 'residencial' ou 'comercial'
    status INTEGER NOT NULL, -- pode ser 'aberta', 'em andamento', 'cancelada' ou 'fechada'
    FOREIGN KEY (id_entidade) REFERENCES entidades (id)
);