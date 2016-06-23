package br.ages.crud.command;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.audio.bo.CapituloBO;
import br.ages.audio.bo.LivroBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;
import br.ages.crud.util.MensagemContantes;

public class AddLivroCommand implements Command {

	private String proxima;
	private LivroBO livroBO;
	private Livro livro;
	private CapituloBO capituloBO;
	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		
		livroBO = new LivroBO();
		capituloBO = new CapituloBO();
		
		proxima = "/main?acao=telaLivro";
		livro = new Livro();
		
		try {
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String autores = request.getParameter("autores");
			String[] capitulosToUpsert = request.getParameterValues("idCapitulosToUpsert");
			String[] capitulosToDelete = request.getParameterValues("idCapitulosToDelete");
			
			livro.setTitulo(titulo);
			livro.setISBN(isbn);
			livro.setAutores(autores);
			
			 boolean result = livroBO.cadastrarLivro(livro);
			 if( result ){
//				 boolean resultCapitulos = capituloBO.cadastrarCapitulos(toCapitulos(capitulosToDelete), toCapitulos(capitulosToUpsert), livro.getIdLivro());
				 
				 proxima = "/main?acao=telaLivro";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo()));
			 }else
				 request.setAttribute("msgErro", MensagemContantes.MSG_ERR_LIVRO_DADOS_INVALIDOS);
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return proxima;
	}
	
	private List<Capitulo> toCapitulos(String[] idCapitulos) {
		List<Capitulo> capitulos = new ArrayList<Capitulo>();
		
		for ( int i = 0; i < idCapitulos.length; i++ ) {
			String idCapituloStr = idCapitulos[i];
			
			Capitulo cap = new Capitulo();
			cap.setIdCapitulo(Integer.valueOf(idCapituloStr));
			capitulos.add(cap);
		}
		
		return capitulos;
	}
}
