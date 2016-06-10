package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class LivroDAO {
	
	public LivroDAO(){
		
	}

	public int cadastraLivro(Livro livro) throws PersistenciaException, SQLException {
	
		Connection conexao = null;
		try {
			Integer idLivro = null;
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tb_livro (Titulo, ISBN, Data_criacao, Data_alteracao)");
			sql.append("values (?,?,?,?)");
			
			// converte a data para data Juliana, data que o banco reconhece
						java.util.Date utilDate = new java.util.Date();
						java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
			
			//cadastra o livro e gera id
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getISBN());
			statement.setDate(3, dataCadastro);
			statement.setDate(4, dataCadastro);
			
			statement.executeUpdate();
			
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset.first()){
				idLivro = resultset.getInt(1);
			}
			
			
			
			return idLivro;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_LIVRO_JA_EXISTENTE.replace("?", livro.getTitulo()));

		} finally {
			conexao.close();
	
		}
	
		
	}

}
