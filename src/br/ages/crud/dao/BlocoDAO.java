package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Status;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Bloco;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class BlocoDAO {
	
	private ArrayList<Bloco> listaBlocos;

	public BlocoDAO() {
		this.listaBlocos = new ArrayList<Bloco>();
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

	public List<Bloco> listarBlocos(Capitulo capitulo) throws PersistenciaException, SQLException {

		Connection conexao = null;

		try {
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("ID_BLOCO,");
			sql.append("LOCAL_CONTEUDO,");
			sql.append("LOCAL_ARQUIVO_AUDIO,");
			sql.append("STATUS_BLOCO,");

			sql.append("from audio_e.tb_bloco ");
			sql.append("WHERE ID_CAPITULO = " + capitulo.getIdCapitulo() + ";");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Bloco bloco = new Bloco();

				bloco.setId_bloco(resultset.getInt("ID_BLOCO"));
				bloco.setLcl_conteudo(resultset.getString("LOCAL_CONTEUDO"));
				bloco.setLcl_arq_audio(resultset.getString("LOCAL_ARQUIVO_AUDIO"));
				bloco.setStatusBloco(Status.valueOf(resultset.getString("STATUS_BLOCO")));

				this.listaBlocos.add(bloco);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}

		return this.listaBlocos;
	}
}
