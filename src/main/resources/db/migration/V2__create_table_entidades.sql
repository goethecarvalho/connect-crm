CREATE TABLE entidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL -- pode ser 'cliente', 'fornecedor' ou 'prestador'
);