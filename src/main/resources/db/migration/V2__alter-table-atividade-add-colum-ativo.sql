ALTER TABLE tb_atividade ADD ativo INTEGER DEFAULT 1;
UPDATE tb_atividade SET ativo = 1 WHERE ativo IS NULL;