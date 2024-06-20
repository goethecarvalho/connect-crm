CREATE TABLE projetos (
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