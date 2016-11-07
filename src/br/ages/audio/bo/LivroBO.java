package br.ages.audio.bo;

import java.sql.SQLException;
import java.util.List;

import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import br.ages.crud.model.StatusLivroEnum;
import br.ages.crud.util.MensagemContantes;

public class LivroBO {

	private LivroDAO livroDAO = new LivroDAO();
	
		
	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	public LivroBO(){}
	
	public int cadastrarLivro(Livro livro) throws PersistenciaException, NegocioException {
				
		try {
			if (validaLivro(livro)) {
				int idLivro = livroDAO.cadastraLivro(livro);
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

	public boolean validaLivro(Livro livro) {
		if (livro==null)
			return false;
		if (livro.getISBN()!=null)
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

	public void excluirLivro() throws PersistenciaException, SQLException{
	
	}
	
	

}
