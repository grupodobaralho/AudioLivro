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
				Gson gsonLivro = new Gson();
				livro = gsonLivro.fromJson(jsonLivro, Livro.class);
				
				if(livro.getIdLivro()==null){
					int idLivro = livroBO.cadastrarLivro(livro);
					livro = livroBO.buscarLivro(idLivro);
					livro.setIdLivro(idLivro);
				}
				else{
					livroBO.atualizarLivro(livro);
					livro = livroBO.buscarLivro(livro.getIdLivro());
				}
				
				Gson gsonCapitulo = new Gson();
				String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
				// Parse from JSON to class
				Capitulo[] capitulosToUpsert = gsonCapitulo.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
				
				int x = 1;
				
				for(Capitulo capitulo : capitulosToUpsert){
					if(capitulo.getIdCapitulo()>0){
						Capitulo capituloAUX = capituloBO.buscaCapitulo(capitulo);
						if(capitulo.getNome().equals(capituloAUX.getNome()) && capitulo.getNumero()== capituloAUX.getNumero()){
							capituloBO.deletarCapitulo(capituloAUX);
						}
						else{
							capituloAUX.setNome(capitulo.getNome());
							capitulo.setNumero(capitulo.getNumero());
							capituloBO.atualizarCapitulo(capituloAUX);
						}
					}
					else{
						capitulo.setLivro(livro);
						capituloBO.cadastrarCapitulo(capitulo);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return proxima;
	}
	
//	private String addLivro(HttpServletRequest request, Livro livro) {
//		try {
//			int idLivro = livroBO.cadastrarLivro(livro);
//			livro.setIdLivro(idLivro);
//			
//			if( livro.getIdLivro() > 0 ){
//				String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
//				// Parse from JSON to class
//				Gson gson = new Gson();
//				Capitulo[] capitulosToUpsert = gson.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
//				
//				boolean resultCapitulos = capituloBO.cadastrarCapitulos(capitulosToUpsert, livro);
//				if ( !resultCapitulos ) {
//					// TODO tratamento de erro quando nao conseguir salvar os capitulos
//				}
//				proxima = livro.getIdLivro() + ";" + MensagemContantes.MSG_SUC_CADASTRO_LIVRO.replace("?", livro.getTitulo());
//				request.setAttribute("JSON", true);
//			}else {
//				proxima = livro.getIdLivro() + ";" + MensagemContantes.MSG_ERR_LIVRO_DADOS_INVALIDOS;
//				request.setAttribute("JSON", true);
//			}
//		}catch(NegocioException | PersistenciaException e) {
//			proxima = livro.getIdLivro() + ";" + e.getMessage();
//			request.setAttribute("JSON", true);
//		}
//		
//		return proxima;
//	}
	
//	private String editLivro(HttpServletRequest request, Livro livro) throws NumberFormatException, PersistenciaException, SQLException {
//		proxima = "/main?acao=telaLivro";
//		
//		String jsonCapitulosToUpsert = request.getParameter("capitulosToUpsert");
//		// Parse from JSON to class
//		Gson gson = new Gson();
//		Capitulo[] capitulosToUpsert = gson.fromJson(jsonCapitulosToUpsert, Capitulo[].class);
//		
//		if ( livroBO.atualizarLivro(livro) ) {
//			capituloBO.processarCapitulos(capitulosToUpsert, livro);
//			
//			proxima = livro.getIdLivro() + ";" + MensagemContantes.MSG_SUC_ATUALIZAR_LIVRO.replace("?", livro.getTitulo());
//			request.setAttribute("JSON", true);
//		}
//		
//		return proxima;
//	}
}
