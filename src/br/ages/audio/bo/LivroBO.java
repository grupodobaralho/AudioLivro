package br.ages.audio.bo;

import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Livro;

public class LivroBO {

	private LivroDAO livroDAO;
	
	public boolean cadastrarLivro(Livro livro) {

		livroDAO = new LivroDAO();
		
		try {
			if (validaLivro(livro)) {
				livroDAO.cadastraLivro(livro);
				return true;
			}
			throw new NegocioException("O cadastro não pode ser efetuado");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	private boolean validaLivro(Livro livro) {
		if (livro.getISBN().length() > 0)
			return true;
		return false;
	}

}
