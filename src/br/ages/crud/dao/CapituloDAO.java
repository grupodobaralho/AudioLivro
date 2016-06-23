package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class CapituloDAO {
	
	public CapituloDAO() {
		
	}
	
	public int cadastrarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		Connection conexao = null;
		try {
			Integer idCapitulo = null;
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tb_Capitulo (id_livro, nome, numero, Data_criacao, Data_alteracao)");
			sql.append("values (?,?,?,?,?)");
			
			// converte a data para data que o banco reconhece
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
			
			//cadastra o capitulo e gera id
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, capitulo.getLivro().getIdLivro());
			statement.setString(2, capitulo.getNome());
			statement.setInt(3, capitulo.getNumero());
			statement.setDate(4, dataCadastro);
			statement.setDate(5, dataCadastro);
			
			statement.executeUpdate();
			
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset.first()){
				idCapitulo = resultset.getInt(1);
			}
			
			return idCapitulo;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_LIVRO_JA_EXISTENTE.replace("?", ""));

		} finally {
			conexao.close();
		}	
	}
}
