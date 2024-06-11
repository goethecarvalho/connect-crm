ALTER TABLE movimentacoes
ALTER COLUMN tipo TYPE integer USING tipo::integer;