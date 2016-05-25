package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.exception.NegocioException;

public class CreateScreenLivroCommand implements Command {

	private String proxima;

	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		proxima = "livro/addLivro.jsp";
		return proxima;
	}

}
