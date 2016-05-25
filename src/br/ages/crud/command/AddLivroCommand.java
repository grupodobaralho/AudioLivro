package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.exception.NegocioException;

public class AddLivroCommand implements Command {

	private String proxima;

	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		proxima = "addLivro.jsp";
		
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return proxima;
	}

}
