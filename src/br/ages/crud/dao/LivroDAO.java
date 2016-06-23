package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class LivroDAO {
	
	private ArrayList<Livro> listarLivros;
	
	public LivroDAO(){
		listarLivros = new ArrayList<>();
	}

	public int cadastraLivro(Livro livro) throws PersistenciaException, SQLException {
	
		Connection conexao = null;
		try {
			Integer idLivro = null;
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tb_livro (Titulo, ISBN, Autores, Data_criacao, Data_alteracao)");
			sql.append("values (?,?,?,?,?)");
			
			// converte a data para data Juliana, data que o banco reconhece
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
			
			//cadastra o livro e gera id
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getISBN());
			statement.setString(3, livro.getAutores());
			statement.setDate(4, dataCadastro);
			statement.setDate(5, dataCadastro);
			
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
	
	public Livro buscarLivro(int idLivro) throws PersistenciaException, SQLException {
		Livro livro = null;
		
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select id_livro, isbn, titulo, autores from tb_livro ");
			sql.append("where id_livro = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idLivro);
			
			ResultSet resultset = statement.executeQuery();
			if(resultset.next()){
				livro = new Livro();
				livro.setIdLivro(resultset.getInt("ID_LIVRO"));
				livro.setISBN(resultset.getString("ISBN"));
				livro.setTitulo(resultset.getString("TITULO"));
				livro.setAutores(resultset.getString("AUTORES"));
			}
			return livro;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Error");

		} finally {
			conexao.close();
		}
	}
	
	public List<Livro> listarLivros() throws PersistenciaException, SQLException {
		Connection conexao = null;
		// tentativa de readaptação do listarUsuarios()
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select ID_LIVRO, ISBN, TITULO, AUTORES ");
			sql.append("from audio_e.tb_livro");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Livro livro = new Livro();
				livro.setIdLivro(resultset.getInt("ID_LIVRO"));
				livro.setISBN(resultset.getString("ISBN"));
				livro.setTitulo(resultset.getString("TITULO"));
				livro.setAutores(resultset.getString("AUTORES"));
				
				listarLivros.add(livro);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarLivros;
	}
}
