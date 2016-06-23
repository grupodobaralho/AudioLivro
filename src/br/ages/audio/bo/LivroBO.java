package br.ages.audio.bo;

import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Livro;

public class LivroBO {

	private LivroDAO livroDAO;
	
	public LivroBO(){}
	
	public int cadastrarLivro(Livro livro) {

		livroDAO = new LivroDAO();
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

}
