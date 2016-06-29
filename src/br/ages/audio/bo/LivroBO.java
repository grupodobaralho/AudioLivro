package br.ages.audio.bo;

import java.sql.SQLException;
import java.util.List;

import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import br.ages.crud.util.MensagemContantes;

public class LivroBO {

	private LivroDAO livroDAO = new LivroDAO();
	
	public LivroBO(){}
	
	public int cadastrarLivro(Livro livro) throws PersistenciaException, NegocioException {
		int idLivro = 0;
		
		try {
			if (validaLivro(livro)) {
				idLivro = livroDAO.cadastraLivro(livro);
				return idLivro;
			}
			throw new NegocioException("O cadastro não pode ser efetuado");
		} catch (Exception e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_LIVRO_JA_EXISTENTE.replace("?", livro.getTitulo()));
		}
	}
	
	public boolean atualizarLivro(Livro livro) throws PersistenciaException, SQLException {
		return livroDAO.atualizarLivro(livro);
	}

	private boolean validaLivro(Livro livro) {
		if (livro.getISBN().length() > 0)
			return true;
		return false;
	}
	
	public Livro buscarLivro(int idLivro) throws PersistenciaException, SQLException {
		return livroDAO.buscarLivro(idLivro);
	}
	
	public List<Livro> listarLivros() throws NegocioException {

		List<Livro> listLivros = null;

		try {
			listLivros = livroDAO.listarLivros();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listLivros;
	}

}
