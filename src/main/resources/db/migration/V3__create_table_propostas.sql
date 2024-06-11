CREATE TABLE propostas (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP not null,
    valor DECIMAL(10, 2),
    entidade_id bigint not null,
    tipo VARCHAR(50) NOT NULL, -- pode ser 'residencial' ou 'comercial'
    status VARCHAR(50) NOT NULL, -- pode ser 'aberta', 'em andamento', 'cancelada' ou 'fechada'
    FOREIGN KEY (entidade_id) REFERENCES entidades (id)
);