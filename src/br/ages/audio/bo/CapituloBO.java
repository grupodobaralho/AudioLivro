package br.ages.audio.bo;

import br.ages.crud.dao.CapituloDAO;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;

public class CapituloBO {
	
private CapituloDAO capituloDAO;
	
	public CapituloBO(){}
	
	public boolean cadastrarCapitulos(Capitulo[] capitulosToInsert, Livro livro) {

		capituloDAO = new CapituloDAO();
		
		try {
			int idCapitulo = 0;
			for (int i = 0; i < capitulosToInsert.length; i++) {
				Capitulo capitulo = capitulosToInsert[i];
				capitulo.setLivro(livro);
				idCapitulo = capituloDAO.cadastrarCapitulo(capitulo);
				if ( idCapitulo == 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

}
