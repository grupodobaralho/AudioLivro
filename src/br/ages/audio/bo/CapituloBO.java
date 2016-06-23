package br.ages.audio.bo;

import java.util.List;

import br.ages.crud.dao.CapituloDAO;
import br.ages.crud.model.Capitulo;

public class CapituloBO {
	
private CapituloDAO capituloDAO;
	
	public CapituloBO(){}
	
	public boolean cadastrarCapitulos(List<Capitulo> capitulosToInsert, List<Capitulo> capitulosToDelete, Integer idLivro) {

		capituloDAO = new CapituloDAO();
		
		try {
			for (int i = 0; i < capitulosToInsert.size(); i++) {
				Capitulo capitulo = capitulosToInsert.get(i);
				capituloDAO.cadastrarCapitulo(capitulo);
			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

}
