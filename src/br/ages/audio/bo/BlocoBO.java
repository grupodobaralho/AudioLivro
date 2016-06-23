package br.ages.audio.bo;

import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.dao.CapituloBlocoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Bloco;

import br.ages.crud.util.MensagemContantes;

public class BlocoBO {
	BlocoDAO blocoDAO = null;
	CapituloBlocoDAO capituloBlocoDAO = null;
	public BlocoBO() {
		blocoDAO = new BlocoDAO();
		capituloBlocoDAO = new CapituloBlocoDAO();
	}

	public void cadastraBloco(Bloco bloco,Integer idCapitulo) throws NegocioException {
		try {
			Integer idBloco = blocoDAO.cadastraBloco(bloco);
			capituloBlocoDAO.cadastraCapituloBloco(idBloco, idCapitulo);
			
		} catch (Exception e) {
			throw new NegocioException(MensagemContantes.MSG_ERR_BLOCO);
		}
	}

}
