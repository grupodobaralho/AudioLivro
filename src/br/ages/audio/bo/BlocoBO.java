package br.ages.audio.bo;

import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.exception.NegocioException;

public class BlocoBO {
	BlocoDAO blocoDAO = null;

	public BlocoBO() {

		blocoDAO = new BlocoDAO();
	}

	public void cadastraBloco(String caminhoPdf, int id, String caminhoAudio, String statusBloco, String tamanhoBloco) {
		try {
			blocoDAO.cadastraBloco(caminhoPdf, id, caminhoAudio, statusBloco, tamanhoBloco);

		} catch (NegocioException e) {

			e.printStackTrace();

		}
	}

}
