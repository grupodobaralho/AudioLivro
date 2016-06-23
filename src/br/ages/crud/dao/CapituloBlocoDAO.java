package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.util.ConexaoUtil;

public class CapituloBlocoDAO {
	public CapituloBlocoDAO(){
	}
	
	public int cadastraCapituloBloco(int idBloco, int idCapitulo) throws NegocioException, SQLException{
		Connection conexao = null;
		try {
			Integer idCapituloBloco = null;
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
			
			sql.append("insert into audio_e.tb_capitulo_bloco (Data_criacao, data_alteracao, id_bloco, id_capitulo)");
			sql.append("values (?, ?, ?, ?, ?, ?)");
			

			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			statement.setDate(1,dataCadastro);
			statement.setDate(2,dataCadastro);
			statement.setInt(3, idBloco);
			statement.setInt(4, idCapitulo);
			
			
			statement.executeUpdate();
			
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset.first()){
				idCapituloBloco = resultset.getInt(1);
			}
			
			return idCapituloBloco;
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}finally {
			conexao.close();
		}	
	}
		
	}

