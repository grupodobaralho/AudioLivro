/***
* Scripts para criacao e insersao de dados
* Base Dados Fluxo AGES
* Casssio Trindade, Daniele Souza e Victor Diehl
* 09/2015
***/


-- alter table ages_e.tb_usuario
-- add constraint U_username unique(usuario);

USE audio_e;


-- DROP TABLE TB_USUARIO;


-- Tabela Usuario
CREATE TABLE tb_usuario (
  ID_USUARIO int(11) NOT NULL AUTO_INCREMENT,
  USUARIO varchar(45) NOT NULL,
  SENHA varchar(45) NOT NULL,
  PERFIL_ACESSO varchar(20) NOT NULL,
  STATUS_USUARIO varchar(20) NOT NULL,
  ID_TIPO_USUARIO int(11) NOT NULL,
  MATRICULA varchar(45) NOT NULL,
  NOME varchar(120) DEFAULT NULL,
  EMAIL varchar(120) DEFAULT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL,
  PRIMARY KEY (ID_USUARIO,MATRICULA),
  UNIQUE KEY MATRICULA_UNIQUE (MATRICULA),
  CONSTRAINT U_USERNAME UNIQUE (USUARIO)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Tabela Tipo Usuario
CREATE TABLE tb_tipo_usuario (
  ID_TIPO_USUARIO int(11) NOT NULL AUTO_INCREMENT,
  NOME varchar(20) NOT NULL,
  DESCRICAO varchar(120) DEFAULT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL,
  PRIMARY KEY (ID_TIPO_USUARIO)
);

-- Inserts
INSERT INTO tb_tipo_usuario VALUES
('1', 'Arquiteto', 'Responsavel pela parte tecnica', '2015-10-01 00:00:00');
INSERT INTO tb_tipo_usuario VALUES
('2', 'Aluno', '', '2015-10-01 00:00:00');
INSERT INTO tb_tipo_usuario VALUES
('3', 'Professor', '', '2015-10-01 00:00:00');
INSERT INTO tb_tipo_usuario VALUES
('4', 'Secretaria', '', '2015-10-01 00:00:00');

INSERT INTO tb_usuario
(ID_USUARIO,USUARIO,SENHA,PERFIL_ACESSO,STATUS_USUARIO,ID_TIPO_USUARIO,MATRICULA,NOME,EMAIL,DATA_INCLUSAO)
VALUES
('10', 'admin', 'admin', 'ADMINISTRADOR', 'ATIVO', '1', '00000', 'Cassio Trindade', 'cassio.trindade@pucrs.br', '2015-10-01 00:00:00');

select * from tb_usuario;

-- -----------------------------------------------------
-- Table audio_e.TB_DOADOR
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS audio_e.TB_DOADOR (
  id_doador INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  Nome VARCHAR(70) NOT NULL,
  CPF VARCHAR(14) NOT NULL,
  Email VARCHAR(70) NOT NULL,
  PRIMARY KEY (id_doador),
  UNIQUE INDEX CPF_UNIQUE (CPF ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table audio_e.TB_BLOCO, unidade mínima de um bloco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS audio_e.TB_BLOCO (
  id_bloco INT NOT NULL,
  Data_criacao DATE NOT,
  Data_alteracao DATE NOT NULL,
  Local_conteudo VARCHAR(100) NOT NULL,
  Local_arquivo_audio VARCHAR(100) NOT NULL,
  Status_bloco VARCHAR(25) NOT NULL,
  Tamanho_bloco VARCHAR(25) NOT NULL,
  PRIMARY KEY (id_bloco))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table audio_e.TB_LIVRO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS audio_e.TB_LIVRO (
  id_livro INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  ISBN VARCHAR(17) NOT NULL,
  Titulo VARCHAR(100) NOT NULL,
  Autores VARCHAR(150) NOT NULL,
  UNIQUE INDEX ISBN_UNIQUE (ISBN ASC),
  PRIMARY KEY (id_livro))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table audio_e.TB_CAPITULO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS audio_e.TB_CAPITULO (
  id_capitulo INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  id_livro INT NOT NULL,
  Nome VARCHAR(45) NOT NULL,
  Numero INT NOT NULL,
  PRIMARY KEY (id_capitulo, id_livro),
  INDEX fk_TB_CAPITULO_TB_LIVRO1_idx (id_livro ASC),
  CONSTRAINT fk_id_livro
    FOREIGN KEY (id_livro)
    REFERENCES audio_e.TB_LIVRO (id_livro)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- ------------------------------------------------------------------
-- Table audio_e.TB_CAPITULO_BLOCO, relação TB_CAPITULO e TB_BLOCO
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS audio_e.TB_CAPITULO_BLOCO (
  id_capitulo_bloco INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  id_bloco INT NOT NULL,
  id_capitulo INT NOT NULL,
  PRIMARY KEY (id_capitulo_bloco, id_bloco, id_capitulo),
  INDEX fk_TB_CAPITULO_BLOCO_TB_BLOCO1_idx (id_bloco ASC),
  INDEX fk_TB_CAPITULO_BLOCO_TB_CAPITULO1_idx (id_capitulo ASC),
  CONSTRAINT fk_id_bloco
    FOREIGN KEY (id_bloco)
    REFERENCES audio_e.TB_BLOCO (id_bloco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_id_capitulo
    FOREIGN KEY (id_capitulo)
    REFERENCES audio_e.TB_CAPITULO (id_capitulo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table audio_e.TB_DOACAO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS audio_e.TB_DOACAO (
  id_doacao INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  id_capitulo_bloco INT NOT NULL,
  id_doador INT NOT NULL,
  Data_limite_doador DATE NOT NULL,
  Data_fechamento_doacao DATE NOT NULL,
  PRIMARY KEY (id_doacao, id_capitulo_bloco, id_doador),
  INDEX fk_TB_DOACAO_TB_CAPITULO_BLOCO1_idx (id_capitulo_bloco ASC),
  INDEX fk_TB_DOACAO_TB_DOADOR1_idx (id_doador ASC),
  CONSTRAINT fk_id_capitulo_bloco
    FOREIGN KEY (id_capitulo_bloco)
    REFERENCES audio_e.TB_CAPITULO_BLOCO (id_capitulo_bloco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_id_doador
    FOREIGN KEY (id_doador)
    REFERENCES audio_e.TB_DOADOR (id_doador)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
