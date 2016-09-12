package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class CapituloDAO {
	
	public CapituloDAO() {}
	
	public boolean cadastrarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		Connection conexao = null;
		try {
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
				return true;
			}
			return false;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_LIVRO_JA_EXISTENTE.replace("?", ""));

		} finally {
			conexao.close();
		}
	}
	
	public boolean atualizarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		boolean returnUpdate = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sysdate = new java.sql.Date(utilDate.getTime());
			
			StringBuilder sql = new StringBuilder();
			sql.append("update tb_Capitulo set ");
			sql.append("nome = ?, ");
			sql.append("numero = ?, ");
			sql.append("Data_alteracao = ? ");
			sql.append("where id_capitulo = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, capitulo.getNome());
			statement.setInt(2, capitulo.getNumero());
			statement.setDate(3, sysdate);
			statement.setInt(4, capitulo.getIdCapitulo());
			statement.execute();
			returnUpdate = true;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnUpdate;
	}
	
	public boolean deletarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		boolean returnDelete = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from tb_Capitulo ");
			sql.append("where id_capitulo = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, capitulo.getIdCapitulo());
			statement.execute();
			returnDelete = true;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnDelete;
	}
	
	public ArrayList<Capitulo> buscarCapitulosDoLivro(Livro livro) throws PersistenciaException, SQLException {
		ArrayList<Capitulo> capitulos = new ArrayList<>();
		Connection conexao = null;
		// tentativa de readaptação do listarUsuarios()
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select ID_CAPITULO, NUMERO, NOME ");
			sql.append("from audio_e.tb_capitulo ");
			sql.append("where id_livro = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, livro.getIdLivro());
			
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Capitulo capitulo = new Capitulo();
				capitulo.setIdCapitulo(resultset.getInt("ID_CAPITULO"));
				capitulo.setNumero(resultset.getInt("NUMERO"));
				capitulo.setNome(resultset.getString("NOME"));
				capitulo.setLivro(livro);
				
				capitulos.add(capitulo);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		
		return capitulos;
	}
}
