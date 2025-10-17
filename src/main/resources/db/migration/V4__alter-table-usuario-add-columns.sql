-- CORREÇÃO 1: Renomear a coluna (Deve ser um comando ALTER TABLE separado)
ALTER TABLE tb_usuario
RENAME COLUMN usuario TO nome;


-- CORREÇÃO 2: Adicionar as novas colunas (Pode ser combinado em um único ALTER TABLE)
ALTER TABLE tb_usuario
ADD COLUMN cpfCnpj VARCHAR(14) UNIQUE, 
ADD COLUMN email VARCHAR(100) UNIQUE;

-- NOTA: O NULL é opcional e implícito no ADD COLUMN, mas removi o NULL
-- explícito da sintaxe para maior clareza, mantendo a opção para ser NULL.