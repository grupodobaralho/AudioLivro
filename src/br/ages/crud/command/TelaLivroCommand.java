package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.ages.audio.bo.CapituloBO;
import br.ages.audio.bo.LivroBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;

public class TelaLivroCommand implements Command{
	private String proxima;
	private LivroBO livroBO;
	private Livro livro;
	private CapituloBO capituloBO;
	private ArrayList<Capitulo> capitulos;

	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		livroBO = new LivroBO();
		capituloBO = new CapituloBO();
		livro = new Livro();
		
		proxima = "livro/addLivro.jsp";
		
		try {
			String idLivro = request.getParameter("livroIdLivro");
			String msg = request.getParameter("msg");
			
			if ( idLivro != null && idLivro.length() > 0 ) {
				livro = livroBO.buscarLivro(Integer.parseInt(idLivro));
				capitulos = capituloBO.buscarCapitulosDoLivro(livro);
				
				request.setAttribute("livro", livro);
				request.setAttribute("capitulos", capitulos);
				
				if ( msg != null && msg.length() > 0 ) {
					request.setAttribute("msgSucesso", msg);
				}
			}
			else if ( msg != null && msg.length() > 0 ) {
				request.setAttribute("msgErro", msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return proxima;
	}

}