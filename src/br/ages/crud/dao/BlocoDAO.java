package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.Livro;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Status;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class BlocoDAO {

public BlocoDAO(){
	
}	
	
	public int cadastraBloco(Bloco bloco) throws NegocioException, SQLException{
		Connection conexao = null;
		try {
			Integer idBloco = null;
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
		
			sql.append("insert into audio_e.tb_bloco (Local_conteudo, Data_criacao, data_alteracao, local_arquivo_audio, status_bloco)");
			sql.append("values (?, ?, ?, ?, ?)");
		
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		
			statement.setString(1, bloco.getLcl_conteudo());
			statement.setDate(2,dataCadastro);
			statement.setDate(3,dataCadastro);
			statement.setString(4,bloco.getLcl_arq_audio());
			statement.setString(5,  bloco.getStatusBloco().toString());
			
			statement.executeUpdate();
		
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset.first()){
				idBloco = resultset.getInt(1);
			}
		
				return idBloco;
		
		
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}finally {
			conexao.close();
		}	
	}
	
	public boolean alteraCaminhoPdf(String caminhoPdf, int idBloco) throws SQLException{
		Connection conexao = null;
		boolean alterado=false;
		
		try {
			
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			
			
		
			sql.append("update audio_e.tb_bloco set Local_conteudo = ?  where id_bloco = ? ");
			
		

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
		
			statement.setString(1, caminhoPdf);
			statement.setInt(2,idBloco);
			
	
		
			statement.executeUpdate();
			
			alterado=true;
			return alterado;
		
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			conexao.close();		
		}	
	}
	
	public boolean excluirBloco(int idBloco) throws SQLException{
		Connection conexao = null;
		boolean excluido=false;
		
		
		try{
			
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE TB_BLOCO SET STATUS_BLOCO = 'EXCLUIDO' WHERE ID_BLOCO = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			
			statement.setInt(1, idBloco);
			
			statement.executeUpdate();			
			
			excluido=true;
			return excluido;
				
			}
		
		catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				throw new SQLException(e);
				
		}
		
		finally{
				conexao.close();
				
		}
		
	}
	
	public Bloco buscarBloco(int idBloco) throws PersistenciaException, SQLException {
		Bloco bloco = null;
		
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID_BLOCO FROM TB_BLOCO ");
			sql.append("WHERE ID_BLOCO = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idBloco);
			
			ResultSet resultset = statement.executeQuery();
			
			if(resultset.next()){
				
				bloco = new Bloco();
				bloco.setId_bloco(resultset.getInt("ID_BLOCO"));	
				bloco.setLcl_conteudo(resultset.getString("LCL_CONTEUDO"));
				bloco.setLcl_arq_audio(resultset.getString("LCL_ARQ_AUDIO"));
				bloco.setStatusBloco(Status.valueOf(resultset.getString("STATUS_BLOCO")));
								
				
			}
			return bloco;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Error");

		} finally {
			conexao.close();
		}
	}
	
}
	
