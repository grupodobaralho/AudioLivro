package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.audio.bo.LivroBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Livro;
import br.ages.crud.util.MensagemContantes;

public class AddLivroCommand implements Command {

	private String proxima;
	private LivroBO livroBO;
	private Livro livro;
	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		
		livroBO = new LivroBO();
		
		proxima = "addLivro.jsp";
		livro = new Livro();
		
		try {
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			
			livro.setTitulo(titulo);
			livro.setISBN(isbn);
			
			 boolean result = livroBO.cadastrarLivro(livro);
			 if(result){
				 proxima = "addLivro.jsp";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo()));
			 }else
				 request.setAttribute("msgErro", MensagemContantes.MSG_ERR_LIVRO_DADOS_INVALIDOS);
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return proxima;
	}

}
