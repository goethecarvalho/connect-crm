CREATE TABLE receitas (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_proposta BIGINT NOT NULL,
    FOREIGN KEY (id_proposta) REFERENCES propostas (id)
);