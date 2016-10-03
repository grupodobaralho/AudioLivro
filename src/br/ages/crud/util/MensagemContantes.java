
package br.ages.crud.util;

public class MensagemContantes {

	public static final String MSG_ERR_CAMPO_OBRIGATORIO = "Campo ? obrigatório!";
	
	public static final String MSG_ERR_CAMPO_NOME_OBRIGATORIO = "Campo Nome obrigatório!";

	public static final String MSG_ERR_CAMPO_SEXO_OBRIGATORIO = "Campo Sexo obrigatório!";
	
	public static final String MSG_ERR_CAMPO_CIDADE_OBRIGATORIO = "Campo Cidade obrigatório!";
	
	public static final String MSG_ERR_CAMPO_ESTADO_OBRIGATORIO = "Campo Estado obrigatório!";
	
	public static final String MSG_ERR_CAMPO_LOGRADOURO_OBRIGATORIO = "Campo Logradouro obrigatório!";
	
	public static final String MSG_ERR_CAMPO_INVALIDO = "Campo ? inválido!";
	
	public static final String MSG_ERR_USUARIO_SENHA_INVALIDOS = "Usuário/Senha inválidos!";
	
	public static final String MSG_ERR_PESSOA_DADOS_INVALIDOS = "Dados da pessoa inválidos ou inconsistentes!";

	public static final String MSG_ERR_USUARIO_DADOS_INVALIDOS = "Dados do Usuário inválidos ou inconsistentes!";
	
	public static final String MSG_ERR_CAMPO_CPF_MAIOR_RECOMENDADO = "Campo ? com mais de 11 caracteres!";
	
	public static final String MSG_ERR_CAMPO_CPF_MENOR_RECOMENDADO = "Campo ? com menos de 11 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO = "Campo ? com mais de 10 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO = "Campo ? com menos de 10 caracteres!";
	
	public static final String MSG_ERR_CAMPO_DATA_INVALIDO = "Ocorreu algum problema no envio da data";	
	
	public static final String MSG_ERR_SENHA_INVALIDA = "A senha do usuario é inválida: Deve ter no mínimo 3 e no máximo 8 caracteres, apenas números e letras!";
	
	public static final String MSG_ERR_MATRICULA_INVALIDA = "Matrícula inválida!";
	
	public static final String MSG_ERR_EMAIL_INVALIDO = "E-mail com formato inválido.<br>não use acentuação e caracteres especiais!"
			+ "<br>Exemplo correto: ages_user@acad.pucrs.br";
	
	public static final String MSG_ERR_NOME_INVALIDO = "Nome com formato inválido.<br>Exemplo correto: João Cardoso";
	
	public static final String MSG_ERR_USUARIO_JA_EXISTENTE = "Usuário ou Matrícula já cadastrados!";
	
	public static final String MSG_ERR_REMOVE_USUARIO_EM_PROJETO = "Este Usuáriojá está em algum projeto e não pode ser removido!";
	
	public static final String MSG_SUC_CADASTRO_PESSOA = "Cadastro de Pessoa efetuado com sucesso!";
	
	public static final String MSG_SUC_CADASTRO_USUARIO = "Usuário ? cadastrado com sucesso!";
	
	public static final String MSG_SUC_EDICAO_USUARIO = "Usuário ? editado com sucesso!";
	
	public static final String MSG_SUC_REMOVE_USUARIO= "Usuário ? removido com sucesso!";
	
	public static final String MSG_SUC_ATUALIZADA_PESSOA = "Atualização de Pessoa efetuada com sucesso!";
	
	public static final String MSG_INF_LOGOUT = "Logout do Usuário efetuado com sucesso!";
	
	public static final String MSG_INF_DENY = "Acesso negado! Você precisa logar primeiro.";
	
	public static final String MSG_ERR_LIVRO_JA_EXISTENTE = "Livro ? já cadastrado";
	
	public static final String MSG_SUC_CADASTRO_LIVRO = "O livro ? foi cadastrado com sucesso!";
	
	public static final String MSG_SUC_ATUALIZAR_LIVRO = "O livro ? foi atualizado com sucesso!";
	
	public static final String MSG_ERR_LIVRO_DADOS_INVALIDOS = "Dados do Livro inválidos ou inconsistentes!";
	
	public static final String MSG_ERR_EXCLUIR_BLOCO_INEXISTENTE = "Bloco informado para exclus�o n�o foi encontrado na base de dados!";
	
	

	//msgs de projeto

	public static final String MSG_ERR_PROJETO_DADOS_INVALIDOS = "Dados do projeto inválidos ou inconsistentes!";
		
	public static final String MSG_ERR_PROJETO_ARQUIVO_INVALIDO = "Arquivo excede o ?Mb ou não é do tipo PDF!";
	
	public static final String MSG_ERR_PROJETO_DATA_INCONSISTENTE = "Data de inicio deve ser anterior é data de fim?!"; //'?' proposital
	
	public static final String MSG_ERR_CADASTRO_PROJETO = "Ocorreu um erro no cadastro do projeto.";

	public static final String MSG_SUC_CADASTRO_PROJETO = "Projeto ? cadastrado com sucesso!";
	
	public static final String MSG_SUC_EDICAO_PROJETO = "Projeto ? editado com sucesso!";

	public static final String MSG_SUC_REMOVE_PROJETO = "Projeto ? removido com sucesso!";

	//msgs de stakeholder
	
	public static final String  MSG_ERR_STAKEHOLDER_JA_EXISTENTE = "Stakeholder ? já cadastrado!";
	
	public static final String MSG_ERR_STAKEHOLDER_DADO_INVALIDO = "Dado do Stakeholder inválido ou inconsistente!";
	
	public static final String MSG_ERR_STAKEHOLDER_NOME_OBRIGATORIO = "Campo Nome obrigatório!";
	

	public static final String MSG_ERR_STAKEHOLDER_NOME_INVALIDO = "Nome com formato inválido.<br>Exemplo: João Pedro";
	
	public static final String MSG_ERR_REMOVE_STAKEHOLDER_EM_PROJETO = "Este stakeholder já está em algum projeto e não pode ser removido!";

	public static final String MSG_SUC_CADASTRO_STAKEHOLDER = "Stakeholder ? cadastrado com sucesso";
	
	public static final String MSG_SUC_EDITA_STAKEHOLDER ="Stakeholder ? editado com sucesso";

	public static final String MSG_SUC_REMOVE_STAKEHOLDER = "Stakeholder de id ? removido com sucesso";
	
	//msgs do Ponto

	public static final String MSG_ERR_CADASTRO_PONTO= "Erro ao salvar o Ponto do Aluno ?";

	public static final String MSG_ERR_CADASTRO_PONTO_DATA_INVALIDA = "Data de Saida menor que Data de Entrada";

	public static final String MSG_SUC_CADASTRO_PONTO = "Cadastrado com SUCESSO o Ponto do Aluno ?";

	public static final String MSG_ERR_PONTO_JA_EXISTENTE = "Ponto ja existente para o Aluno ? na data";

	public static final String MSG_ERR_BLOCO = "Erro bloco";

}

