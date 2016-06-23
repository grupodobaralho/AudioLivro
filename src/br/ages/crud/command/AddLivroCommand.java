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
			String idLivro = request.getParameter("idLivro");
			String jsonLivro = request.getParameter("livro");
			
			if ( idLivro != null && idLivro.length() > 0 ) {
				proxima = editLivro(request, idLivro);
			}
			else if ( jsonLivro != null ) {
				proxima = addLivro(request);
			}
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return proxima;
	}
	
	private String addLivro(HttpServletRequest request) {
		String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
		String jsonLivro = request.getParameter("livro");
		
		// Parse from JSON to class
		Gson gson = new Gson();
		Capitulo[] capitulosToUpsert = gson.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
		livro = gson.fromJson(jsonLivro, Livro.class);
		
		livro.setIdLivro(livroBO.cadastrarLivro(livro));
		if( livro.getIdLivro() > 0 ){
			boolean resultCapitulos = capituloBO.cadastrarCapitulos(capitulosToUpsert, livro);
			if ( !resultCapitulos ) {
				// TODO tratamento de erro quando nao conseguir salvar os capitulos
			}
			proxima = livro.getIdLivro() + ";1";
			request.setAttribute("JSON", true);
			request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo()));
		}else {
			request.setAttribute("msgErro", MensagemContantes.MSG_ERR_LIVRO_DADOS_INVALIDOS);
		}
		
		return proxima;
	}
	
	private String editLivro(HttpServletRequest request, String idLivro) throws NumberFormatException, PersistenciaException, SQLException {
		String msg = request.getParameter("msg");
		
		proxima = "/main?acao=telaLivro";
		
		if ( msg != null && msg.length() > 0 ) {
			livro = livroBO.buscarLivro(Integer.parseInt(idLivro));
			request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo()));
		}
		
		return proxima;
	}
}
