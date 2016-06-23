package br.ages.audio.bo;

import java.sql.SQLException;

import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;

public class LivroBO {

	private LivroDAO livroDAO = new LivroDAO();
	
	public LivroBO(){}
	
	public int cadastrarLivro(Livro livro) {
		int idLivro = 0;
		
		try {
			if (validaLivro(livro)) {
				idLivro = livroDAO.cadastraLivro(livro);
				return idLivro;
			}
			throw new NegocioException("O cadastro não pode ser efetuado");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return 0;
	}

	private boolean validaLivro(Livro livro) {
		if (livro.getISBN().length() > 0)
			return true;
		return false;
	}
	
	public Livro buscarLivro(int idLivro) throws PersistenciaException, SQLException {
		return livroDAO.buscarLivro(idLivro);
	}

}
