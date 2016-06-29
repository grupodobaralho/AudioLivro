package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.ages.audio.bo.CapituloBO;
import br.ages.audio.bo.LivroBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
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
			String jsonLivro = request.getParameter("livro");
			if ( jsonLivro.length() > 0 ) {
				// Parse from JSON to class
				Gson gson = new Gson();
				livro = gson.fromJson(jsonLivro, Livro.class);
				
				if ( livro.getIdLivro() != null && livro.getIdLivro() > 0 ) {
					proxima = editLivro(request, livro);
				}
				else if ( jsonLivro != null ) {
					proxima = addLivro(request, livro);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return proxima;
	}
	
	private String addLivro(HttpServletRequest request, Livro livro) {
		livro.setIdLivro(livroBO.cadastrarLivro(livro));
		if( livro.getIdLivro() > 0 ){
			String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
			// Parse from JSON to class
			Gson gson = new Gson();
			Capitulo[] capitulosToUpsert = gson.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
			
			boolean resultCapitulos = capituloBO.cadastrarCapitulos(capitulosToUpsert, livro);
			if ( !resultCapitulos ) {
				// TODO tratamento de erro quando nao conseguir salvar os capitulos
			}
			proxima = livro.getIdLivro() + ";" + MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo());
			request.setAttribute("JSON", true);
		}else {
			request.setAttribute("msgErro", MensagemContantes.MSG_ERR_LIVRO_DADOS_INVALIDOS);
		}
		
		return proxima;
	}
	
	private String editLivro(HttpServletRequest request, Livro livro) throws NumberFormatException, PersistenciaException, SQLException {
		proxima = "/main?acao=telaLivro";
		
		String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
		// Parse from JSON to class
		Gson gson = new Gson();
		Capitulo[] capitulosToUpsert = gson.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
		
		if ( livroBO.atualizarLivro(livro) ) {
			capituloBO.atualizarCapitulos(capitulosToUpsert);
			
			proxima = livro.getIdLivro() + ";" + MensagemContantes.MSG_SUC_ATUALIZAR_LIVRO.replace("?", livro.getTitulo());
			request.setAttribute("JSON", true);
		}
		
		return proxima;
	}
}
