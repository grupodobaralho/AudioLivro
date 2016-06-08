package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class BlocoDAO {

public BlocoDAO(){
	
}	
	
public void cadastraBloco(String caminhoPdf, int id, String caminhoAudio, String statusBloco, String tamanhoBloco ) throws NegocioException{
	try {

		Connection conexao = ConexaoUtil.getConexao();
		StringBuilder sql = new StringBuilder();
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
		
		sql.append("insert into audio_e.tb_bloco (Local_conteudo, id_bloco, Data_criacao, data_alteracao, local_arquivo_audio, status_bloco, tamanho_bloco)");
		sql.append("values (?, ?, ?, ?, ?, ?, ?)");
		

		PreparedStatement statement = conexao.prepareStatement(sql.toString());
		statement.setString(1, caminhoPdf);
		statement.setInt(2, id);
		statement.setDate(3,dataCadastro);
		statement.setDate(4,dataCadastro);
		statement.setString(5, caminhoAudio);
		statement.setString(6, statusBloco);
		statement.setString(7, tamanhoBloco);
		
		statement.executeUpdate();
		
	} catch (ClassNotFoundException | SQLException e) {
		throw new NegocioException(MensagemContantes.MSG_ERR_BLOCO);
	}
	
}
	
}