-- Script para criar a tabela 'tb_atividade' no PostgreSQL

CREATE TABLE tb_atividade (
    id SERIAL PRIMARY KEY, -- SERIAL para autoincremento e chave primária
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    contato VARCHAR(45),
    foneContato VARCHAR(45),
    regrasUso VARCHAR(255),
    valor DECIMAL(10,2),
    regrasPagamento VARCHAR(255),
    duracao INTEGER, -- INT no MySQL é INTEGER no PostgreSQL
    categoria VARCHAR(30),
    localizacao VARCHAR(255)
);

-- Script para criar a tabela 'tb_turista' no PostgreSQL

CREATE TABLE tb_turista (
    id SERIAL PRIMARY KEY, -- SERIAL para autoincremento e chave primária
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(100)
);

-- Script para criar a tabela 'tb_inscricao' no PostgreSQL

CREATE TABLE tb_inscricao (
    id SERIAL, -- SERIAL para autoincremento (não é PRIMARY KEY por si só aqui)
    nrInscricao VARCHAR(45) NOT NULL,
    atividade_id INTEGER NOT NULL, -- INT no MySQL é INTEGER no PostgreSQL
    turista_id INTEGER NOT NULL,   -- INT no MySQL é INTEGER no PostgreSQL

    -- Chave primária composta
    PRIMARY KEY (id, atividade_id, turista_id),

    -- Chaves estrangeiras
    CONSTRAINT fk_tb_inscricoes_atividade FOREIGN KEY (atividade_id) REFERENCES tb_atividade (id),
    CONSTRAINT fk_tb_inscricoes_turista FOREIGN KEY (turista_id) REFERENCES tb_turista (id)
);