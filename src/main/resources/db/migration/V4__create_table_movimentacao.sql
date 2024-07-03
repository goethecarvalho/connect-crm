CREATE TABLE movimentacoes (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_entidade BIGINT NOT NULL,
    id_proposta BIGINT NULL,
    tipo INTEGER NOT NULL CHECK (tipo >= 0),
    FOREIGN KEY (id_entidade) REFERENCES entidades (id)
);