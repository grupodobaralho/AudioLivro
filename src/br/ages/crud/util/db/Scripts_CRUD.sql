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
-- Table TB_DOADOR
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TB_DOADOR (
  id_doador INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  Nome VARCHAR(70) NOT NULL,
  CPF VARCHAR(14) NOT NULL,
  Email VARCHAR(70) NOT NULL,
  PRIMARY KEY (id_doador)) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table TB_BLOCO, unidade mínima de texto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TB_BLOCO (
  id_bloco INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  Local_conteudo VARCHAR(100) NOT NULL,
  Local_arquivo_audio VARCHAR(100) NOT NULL,
  Status_bloco VARCHAR(25) NOT NULL,
  Tamanho_bloco VARCHAR(25) NOT NULL,
  PRIMARY KEY (id_bloco))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table TB_LIVRO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TB_LIVRO (
  id_livro INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  Titulo VARCHAR(100) NOT NULL,
  Autores VARCHAR(150) NOT NULL,
  PRIMARY KEY (id_livro))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table TB_LIVRO_BLOCO, relação tb_livro com tb_bloco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TB_LIVRO_BLOCO (
  id_livro_bloco INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  id_livro INT NOT NULL,
  id_bloco INT NOT NULL,
  PRIMARY KEY (id_livro_bloco),
  INDEX id_livro_idx (id_livro ASC),
  INDEX id_bloco_idx (id_bloco ASC),
  CONSTRAINT id_livro
    FOREIGN KEY (id_livro)
    REFERENCES TB_LIVRO (id_livro)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT id_bloco
    FOREIGN KEY (id_bloco)
    REFERENCES TB_BLOCO (id_bloco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------------
-- Table TB_DOACAO, controle de doações
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS TB_DOACAO (
  id_doacao INT NOT NULL,
  id_doador INT NOT NULL,
  id_livro_bloco INT NOT NULL,
  Data_criacao DATE NOT NULL,
  Data_alteracao DATE NOT NULL,
  Data_limite_doador DATE NOT NULL,
  Data_fechamento_doacao DATE NOT NULL,
  PRIMARY KEY (id_doacao),
  INDEX id_doador_idx (id_doador ASC),
  INDEX id_livro_bloco_idx (id_livro_bloco ASC),
  CONSTRAINT id_doador
    FOREIGN KEY (id_doador)
    REFERENCES TB_DOADOR (id_doador)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT id_livro_bloco
    FOREIGN KEY (id_livro_bloco)
    REFERENCES TB_LIVRO_BLOCO (id_livro_bloco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
