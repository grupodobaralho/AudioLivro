package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.exception.NegocioException;

public class ModeloCommand implements Command {
private String proxima;
	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		proxima = "modelo.jsp";
		return proxima;
	}

}
