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
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Doador;
import br.ages.crud.model.Livro;
import br.ages.crud.model.PerfilAcessoEnum;
import br.ages.crud.model.StatusBlocoEnum;
import br.ages.crud.model.StatusCapituloEnum;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class BlocoDAO {

	public BlocoDAO() {

	}

	public int cadastraBloco(Bloco bloco) throws NegocioException, SQLException {
		Connection conexao = null;
		try {
			Integer idBloco = null;
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());

			sql.append(
					"insert into audio_e.tb_bloco (Local_conteudo, Data_criacao, data_alteracao, local_arquivo_audio, status_bloco)");
			sql.append("values (?, ?, ?, ?, ?)");

			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, bloco.getLcl_conteudo());
			statement.setDate(2, dataCadastro);
			statement.setDate(3, dataCadastro);
			statement.setString(4, bloco.getLcl_arq_audio());
			statement.setString(5, bloco.getStatusBloco().toString());

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idBloco = resultset.getInt(1);
			}

			return idBloco;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		} finally {
			conexao.close();
		}
	}

	public boolean alteraCaminhoPdf(String caminhoPdf, int idBloco) throws SQLException {
		Connection conexao = null;
		boolean alterado = false;

		try {

			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();

			sql.append("update audio_e.tb_bloco set Local_conteudo = ?  where id_bloco = ? ");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());

			statement.setString(1, caminhoPdf);
			statement.setInt(2, idBloco);

			statement.executeUpdate();

			alterado = true;
			return alterado;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			conexao.close();
		}
	}

	public boolean excluirBloco(int idBloco) throws SQLException {
		Connection conexao = null;
		boolean excluido = false;

		try {

			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE TB_BLOCO SET STATUS_BLOCO = 'EXCLUIDO' WHERE ID_BLOCO = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());

			statement.setInt(1, idBloco);

			statement.executeUpdate();

			excluido = true;
			return excluido;

		}

		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);

		}

		finally {
			conexao.close();

		}

	}

	public Bloco buscarBlocoID(int idBloco) throws PersistenciaException, SQLException {
		Bloco bloco = null;

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID_BLOCO, LCL_CONTEUDO, LCL_ARQ_AUDIO, _STATUS_BLOCO");
			sql.append("WHERE ID_BLOCO = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idBloco);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				bloco = new Bloco();
				bloco.setId_bloco(resultset.getInt("ID_BLOCO"));
				bloco.setLcl_conteudo(resultset.getString("LCL_CONTEUDO"));
				bloco.setLcl_arq_audio(resultset.getString("LCL_ARQ_AUDIO"));
				bloco.setStatusBloco(StatusBlocoEnum.valueOf(resultset.getString("STATUS_BLOCO")));

			}
			return bloco;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Error");

		} finally {
			conexao.close();

		}
	}

	public boolean alterarStatusDoBloco(int idBloco, StatusBlocoEnum status)
			throws PersistenciaException, SQLException {
		Connection conexao = null;

		try {

			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE TB_BLOCO SET STATUS_BLOCO = ? WHERE ID_BLOCO = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());

			statement.setString(1, status.toString());
			statement.setInt(2, idBloco);

			statement.executeUpdate();

			return true;

		}

		catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException("Error");

		} finally {
			conexao.close();

		}

	}

	// Recebe por parâmetro um capítulo e retorna uma lista com todos os blocos
	// que pertencem a ele
	public List<Bloco> buscarBlocosDoCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		List<Bloco> blocos = null;
		Connection conexao = null;

		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM TB_CAPITULO C INNER JOIN TB_CAPITULO_BLOCO CB ");
			sql.append("ON C.ID_CAPITULO = CB.ID_CAPITULO ");
			sql.append("INNER JOIN TB_BLOCO B ");
			sql.append("ON CB.ID_BLOCO = B.ID_BLOCO ");
			sql.append("WHERE C.ID_CAPITULO = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, capitulo.getIdCapitulo());

			ResultSet resultset = statement.executeQuery();
			//
			blocos = new ArrayList<Bloco>();
			//
			while (resultset.next()) {
				Bloco bloco = new Bloco();
				bloco.setId_bloco(resultset.getInt("B.ID_BLOCO"));
				// Data_criacao
				// Data_alteracao
				bloco.setLcl_conteudo(resultset.getString("B.LOCAL_CONTEUDO"));
				bloco.setLcl_arq_audio(resultset.getString("B.LOCAL_ARQUIVO_AUDIO"));
				bloco.setStatusBloco(StatusBlocoEnum.valueOf(resultset.getString("STATUS_BLOCO")));
				// Tamanho_arquivopdf
				// Tamanho_arquivomp3
				// Ordem_bloco
				//
				blocos.add(bloco);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return blocos;
	}

	public boolean associarDoadorBloco(int idBloco, Doador doador) {
		// TODO Auto-generated method stub
		return false;
	}

}
