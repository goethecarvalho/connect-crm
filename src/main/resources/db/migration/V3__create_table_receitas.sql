CREATE TABLE receitas (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    id_entidade BIGINT NOT NULL,
    tipo INTEGER NOT NULL CHECK (tipo >= 0),
    FOREIGN KEY (id_entidade) REFERENCES entidades (id)
);