package br.ages.audio.bo;

import java.sql.SQLException;

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

	public int cadastraBloco(Bloco bloco,Integer idCapitulo) throws NegocioException {
		try {
			Integer idBloco = blocoDAO.cadastraBloco(bloco);
			capituloBlocoDAO.cadastraCapituloBloco(idBloco, idCapitulo);
			return idBloco;
		} catch (Exception e) {
			throw new NegocioException(MensagemContantes.MSG_ERR_BLOCO);
		}
	}
	
	public void alteraCaminho(int idBloco, String caminhoPdf){
		try {
			blocoDAO.alteraCaminhoPdf(caminhoPdf, idBloco);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
