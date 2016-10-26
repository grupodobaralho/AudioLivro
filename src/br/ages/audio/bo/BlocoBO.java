package br.ages.audio.bo;

import java.sql.SQLException;

import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.dao.CapituloBlocoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.StatusBloco;
import br.ages.crud.util.MensagemContantes;

public class BlocoBO {
	BlocoDAO blocoDAO;
	CapituloBlocoDAO capituloBlocoDAO;

	public BlocoBO() {
		blocoDAO = new BlocoDAO();
		capituloBlocoDAO = new CapituloBlocoDAO();
	}

	public void setBlocoDAO(BlocoDAO blocoDAO) {
		this.blocoDAO = blocoDAO;
	}

	public void setCapituloBlocoDAO(CapituloBlocoDAO capituloBlocoDAO) {
		this.capituloBlocoDAO = capituloBlocoDAO;
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
	
	public boolean alteraCaminho(String caminhoPdf, int idBloco){
		boolean alterado = false;
		try {
			blocoDAO.alteraCaminhoPdf(caminhoPdf, idBloco);
			alterado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterado;		
	}
		
	public boolean excluirBloco(int idBloco) throws NegocioException {
		boolean idBlocoExcluido = false;	
		try{
			if (blocoDAO.buscarBlocoID(idBloco).getStatusBloco() != StatusBloco.EM_GRAVACAO)
			{
				idBlocoExcluido = blocoDAO.excluirBloco(idBloco);				
			}
			return idBlocoExcluido;
		}	
		
		catch (Exception e){
				throw new NegocioException(MensagemContantes.MSG_ERR_EXCLUIR_BLOCO_INEXISTENTE); 
		}
		
	}
		
	public Bloco buscarBlocoID (int idBloco) throws PersistenciaException, SQLException{
		 
		return blocoDAO.buscarBlocoID(idBloco);
		
	}
	
		
	
}
	