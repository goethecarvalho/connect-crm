CREATE TABLE entidades (
    id SERIAL PRIMARY KEY,          -- Cria uma coluna 'id' que é um inteiro auto-incremental e chave primária
    nome VARCHAR(255) NOT NULL,     -- Cria uma coluna 'nome' que é uma string com no máximo 255 caracteres e não pode ser nula
    tipo INTEGER NOT NULL CHECK (tipo >= 0)  -- Cria uma coluna 'tipo' que é um inteiro, não pode ser nulo, e deve ser não-negativo
);
