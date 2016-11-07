package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.ages.audio.bo.CapituloBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;
import br.ages.crud.model.StatusBlocoEnum;
import br.ages.crud.model.StatusCapituloEnum;
import br.ages.crud.model.StatusLivroEnum;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class LivroDAO {

	private ArrayList<Livro> listarLivros;

	public LivroDAO() {
		listarLivros = new ArrayList<>();
	}

	public int cadastraLivro(Livro livro) throws PersistenciaException, SQLException {

		Connection conexao = null;
		try {
			Integer idLivro = null;
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into tb_livro (Titulo, ISBN, Autores, Data_criacao, Data_alteracao, Status)");
			sql.append("values (?,?,?,?,?,?)");

			// converte a data para data Juliana, data que o banco reconhece
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());

			// cadastra o livro e gera id
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getISBN());
			statement.setString(3, livro.getAutores());
			statement.setDate(4, dataCadastro);
			statement.setDate(5, dataCadastro);
			statement.setString(6, StatusLivroEnum.INCOMPLETO.toString());

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idLivro = resultset.getInt(1);
			}
			return idLivro;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(
					MensagemContantes.MSG_ERR_LIVRO_JA_EXISTENTE.replace("?", livro.getTitulo()));
		} finally {
			conexao.close();
		}
	}

	public boolean atualizarLivro(Livro livro) throws PersistenciaException, SQLException {
		boolean returnUpdate = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sysdate = new java.sql.Date(utilDate.getTime());

			StringBuilder sql = new StringBuilder();
			sql.append("update tb_livro set ");
			sql.append("Titulo = ?, ");
			sql.append("ISBN = ?, ");
			sql.append("Autores = ?, ");
			sql.append("Data_alteracao = ? ");
			sql.append("where id_livro = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, livro.getTitulo());
			statement.setString(2, livro.getISBN());
			statement.setString(3, livro.getAutores());
			statement.setDate(4, sysdate);
			statement.setInt(5, livro.getIdLivro());
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

	public boolean excluirLivro(int idLivro) throws PersistenciaException, SQLException {
		boolean returnUpdate = false;
		Connection conexao = null;
		try {
			
			Livro livro = buscarLivro(idLivro);
			for(Capitulo capitulo : livro.getCapitulos()){
				for(Bloco bloco : capitulo.getBlocos()){
					if(bloco.getStatusBloco() == StatusBlocoEnum.EM_GRAVACAO){
						return false;
					}
				}
			}
			
			conexao = ConexaoUtil.getConexao();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sysdate = new java.sql.Date(utilDate.getTime());

			StringBuilder sql = new StringBuilder();
			sql.append("update tb_Livro set ");
			sql.append("Data_alteracao = ? , ");
			sql.append("status = ? ");
			sql.append("where id_livro = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setDate(1, sysdate);
			statement.setString(2, StatusLivroEnum.EXCLUIDO.toString());
			statement.setInt(3, idLivro);
			statement.execute();
			returnUpdate = true;

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();

		}
		return returnUpdate;
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
			if (resultset.next()) {
				livro = new Livro();
				livro.setIdLivro(resultset.getInt("ID_LIVRO"));
				livro.setISBN(resultset.getString("ISBN"));
				livro.setTitulo(resultset.getString("TITULO"));
				livro.setAutores(resultset.getString("AUTORES"));
				
				CapituloBO capituloBO = new CapituloBO();
				ArrayList<Capitulo> capitulosLivro = capituloBO.buscarCapitulosDoLivro(livro);

				livro.setCapitulos(capitulosLivro);
			}
			return livro;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Error");

		} finally {
			conexao.close();
		}
	}

	public List<Livro> listarLivros() throws PersistenciaException, SQLException, NegocioException {
		Connection conexao = null;
		// tentativa de readaptação do listarUsuarios()
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select ID_LIVRO, ISBN, TITULO, AUTORES, STATUS ");
			sql.append("from audio_e.tb_livro");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Livro livro = new Livro();
				livro.setIdLivro(resultset.getInt("ID_LIVRO"));
				livro.setISBN(resultset.getString("ISBN"));
				livro.setTitulo(resultset.getString("TITULO"));
				livro.setAutores(resultset.getString("AUTORES"));
				livro.setStatus(StatusLivroEnum.valueOf(resultset.getString("STATUS")));

				CapituloBO capituloBO = new CapituloBO();
				ArrayList<Capitulo> capitulosLivro = capituloBO.buscarCapitulosDoLivro(livro);

				livro.setCapitulos(capitulosLivro);

				listarLivros.add(livro);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarLivros;
	}

	public List<Livro> listarLivros(StatusLivroEnum status) throws PersistenciaException, SQLException {
		Connection conexao = null;

		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select ID_LIVRO, ISBN, TITULO, AUTORES ");
			sql.append("from audio_e.tb_livro");
			sql.append("where STATUS =?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());

			statement.setString(1, status.toString());

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
