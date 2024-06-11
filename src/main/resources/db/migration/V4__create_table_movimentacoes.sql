CREATE TABLE movimentacoes (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_entidade BIGINT NOT NULL,
    id_proposta BIGINT NOT NULL,
    tipo VARCHAR(50) NOT NULL CHECK (tipo IN ('DEBITO', 'CREDITO')), -- pode ser 'debito' ou 'credito'
    FOREIGN KEY (id_proposta) REFERENCES propostas (id)
);