/***
* Scripts para criacao e insersao de dados
* Base Dados Fluxo AGES
* Casssio Trindade, Daniele Souza e Victor Diehl
* 09/2015
***/


-- alter table ages_e.tb_usuario
-- add constraint U_username unique(usuario);

USE audio_e;

-- DROP TABLE TB_TIPO_USUARIO;
-- DROP TABLE TB_PROJETO_USUARIO;
-- DROP TABLE TB_PROJETO_STAKEHOLDERS;
-- DROP TABLE TB_USUARIO;
-- DROP TABLE TB_PROJETO;
-- DROP TABLE TB_STAKEHOLDERS;

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

-- Tabela Projeto
  CREATE TABLE tb_projeto (
  ID_PROJETO int(11) NOT NULL AUTO_INCREMENT,
  NOME_PROJETO varchar(120) NOT NULL, 
  STATUS_PROJETO varchar(10) NOT NULL,  
  WORKSPACE varchar(60) DEFAULT NULL,  
  DATA_INICIO datetime NOT NULL, 
  DATA_FIM datetime DEFAULT NULL,
  DATA_FIM_PREVISTO datetime NOT NULL,
  DATA_INCLUSAO datetime NOT NULL,
  PRIMARY KEY (ID_PROJETO) 
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
 
-- Tabela Stakeholders
CREATE TABLE tb_stakeholders (
  ID_STAKEHOLDER int(11) NOT NULL AUTO_INCREMENT,
  NOME_STAKEHOLDER varchar(45) NOT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL, 
  PRIMARY KEY (ID_STAKEHOLDER)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Tabela Projeto/Usuario
CREATE TABLE tb_projeto_usuario (
  ID_PROJETO int(11) NOT NULL,
  ID_USUARIO int(11) NOT NULL,
  PRIMARY KEY (ID_PROJETO,ID_USUARIO),
  KEY fk_usuario_idx (ID_USUARIO),
  CONSTRAINT fk_projeto FOREIGN KEY (ID_PROJETO) REFERENCES tb_projeto (ID_PROJETO) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_usuario FOREIGN KEY (ID_USUARIO) REFERENCES tb_usuario (ID_USUARIO) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabela Projeto/Stakeholders
CREATE TABLE tb_projeto_stakeholders (
  ID_PROJETO int(11) NOT NULL,
  ID_STAKEHOLDER int(11) NOT NULL,
  PRIMARY KEY (ID_PROJETO,ID_STAKEHOLDER),
  KEY fk_stakeholder_idx (ID_STAKEHOLDER),
  CONSTRAINT fk_projeto_s FOREIGN KEY (ID_PROJETO) REFERENCES tb_projeto (ID_PROJETO) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_stakeholder FOREIGN KEY (ID_STAKEHOLDER) REFERENCES tb_stakeholders (ID_STAKEHOLDER) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabela para o Registro do Ponto, horas fora aula na agencia.
CREATE TABLE tb_ponto (
  id_ponto INT NOT NULL AUTO_INCREMENT COMMENT '',
  data_entrada DATETIME NULL COMMENT '',
  hora_entrada DATETIME NULL COMMENT '',
  data_saida DATETIME NULL COMMENT '',
  hora_saida DATETIME NULL COMMENT '',
  id_usuario_aluno INT NULL COMMENT '',
  id_usuario_responsavel INT NULL COMMENT '',
  status_ponto VARCHAR(30) NULL COMMENT '',
  PRIMARY KEY (id_ponto)  COMMENT '')
COMMENT = 'Tabela para o Registro do Ponto, horas fora aula na agencia. ';


-- Tabelas da aplicação
-- Tabela para Armazenar a menor unidade de um livro
CREATE TABLE `audio_e`.`tb_blocos` (
  `id_blocos` INT NOT NULL COMMENT '',
  `conteudo` VARCHAR(45) NULL COMMENT '',
  `data_criacao` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id_blocos`)  COMMENT '');