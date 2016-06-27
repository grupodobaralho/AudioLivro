package br.ages.audio.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ages.crud.dao.CapituloDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;

public class CapituloBO {
	
	private CapituloDAO capituloDAO;

	public CapituloBO() {
		capituloDAO = new CapituloDAO();
	}
	
	public boolean cadastrarCapitulos(Capitulo[] capitulosToInsert, Livro livro) {

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
	
	public ArrayList<Capitulo> buscarCapitulosDoLivro(Livro livro) throws PersistenciaException, SQLException {
		return capituloDAO.buscarCapitulosDoLivro(livro);
	}
}
