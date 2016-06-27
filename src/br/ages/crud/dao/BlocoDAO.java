package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
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

	public void cadastraBloco(String caminhoPdf, int id, String caminhoAudio, String statusBloco, String tamanhoBloco)
			throws NegocioException {
		try {

			Connection conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());

			sql.append(
					"insert into audio_e.tb_bloco (Local_conteudo, id_bloco, Data_criacao, data_alteracao, local_arquivo_audio, status_bloco, tamanho_bloco)");
			sql.append("values (?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, caminhoPdf);
			statement.setInt(2, id);
			statement.setDate(3, dataCadastro);
			statement.setDate(4, dataCadastro);
			statement.setString(5, caminhoAudio);
			statement.setString(6, statusBloco);
			statement.setString(7, tamanhoBloco);

			statement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new NegocioException(MensagemContantes.MSG_ERR_BLOCO);
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
			sql.append("WHERE ID_CAPITULO = " + capitulo.getId_capitulo() + ";");

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