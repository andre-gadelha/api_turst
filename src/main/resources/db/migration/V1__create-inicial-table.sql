CREATE TABLE tb_atividade (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(255),
  contato VARCHAR(45),
  foneContato VARCHAR(45),
  regrasUso VARCHAR(255),
  valor DECIMAL(10,2),
  regrasPagamento VARCHAR(255),
  duracao INT,
  categoria VARCHAR(30),
  localizacao VARCHAR(255)
);

CREATE TABLE tb_turista (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  telefone VARCHAR(100)
);

CREATE TABLE tb_inscricao (
  id INT AUTO_INCREMENT NOT NULL,
  nrInscricao VARCHAR(45) NOT NULL,
  atividade_id INT NOT NULL,
  turista_id INT NOT NULL,
  PRIMARY KEY (id, atividade_id, turista_id),

  CONSTRAINT fk_tb_inscricoes_atividade FOREIGN KEY (atividade_id) REFERENCES tb_atividade (id),
  CONSTRAINT fk_tb_inscricoes_turista FOREIGN KEY (turista_id) REFERENCES tb_turista (id)
);