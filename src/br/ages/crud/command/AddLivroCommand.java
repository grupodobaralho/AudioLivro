package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

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
		
		proxima = "json";
		livro = new Livro();
		
		try {
			String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
			String jsonCapitulosToDelete = request.getParameter("capitulosToDelete");
			String jsonLivro = request.getParameter("livro");
			
			// Parse from JSON to class
			Gson gson = new Gson();
			Capitulo[] capitulosToUpsert = gson.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
			Capitulo[] capitulosToDelete = gson.fromJson(jsonCapitulosToDelete, Capitulo[].class);
			livro = gson.fromJson(jsonLivro, Livro.class);
			
			livro.setIdLivro(livroBO.cadastrarLivro(livro));
			if( livro.getIdLivro() > 0 ){
				boolean resultCapitulos = capituloBO.cadastrarCapitulos(capitulosToUpsert, capitulosToDelete, livro);
				 
				if ( !resultCapitulos ) {
					// TODO tratamento de erro quando nao conseguir salvar os capitulos
				}
			
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo()));
			}else {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_LIVRO_DADOS_INVALIDOS);
			}
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return proxima;
	}
}
