package br.ages.crud.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.audio.bo.LivroBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;

public class RemoveLivroCommand implements Command {

	private String proxima;
	private LivroBO livroBO;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proxima = "livro/listLivro.jsp";

		try {
			String idLivroString = request.getParameter("idLivro");
			int idLivro = Integer.parseInt(idLivroString);
			
			livroBO = new LivroBO();
			
			livroBO.excluirLivro(idLivro);
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}

}
