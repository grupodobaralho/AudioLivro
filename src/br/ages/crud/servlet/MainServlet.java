package br.ages.crud.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.ages.crud.command.AddLivroCommand;
import br.ages.crud.command.AddUserCommand;
import br.ages.crud.command.Command;
import br.ages.crud.command.CreateScreenUserCommand;
import br.ages.crud.command.EditUserCommand;
import br.ages.crud.command.ListLivroCommand;
import br.ages.crud.command.ListUserCommand;
import br.ages.crud.command.LoginCommand;
import br.ages.crud.command.LogoutCommand;
import br.ages.crud.command.ModeloCommand;
import br.ages.crud.command.RemoveLivroCommand;
import br.ages.crud.command.RemoveUserCommand;
import br.ages.crud.command.SenhaCommand;
import br.ages.crud.command.TelaBlocoCommand;
import br.ages.crud.command.TelaLivroCommand;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.LogParametrosSession;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	Logger logger = Logger.getLogger("servlet.MainServlet");
	private static final long serialVersionUID = 1L;
	private Map<String, Command> comandos = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		
		comandos.put("login", new LoginCommand());
		comandos.put("logout", new LogoutCommand());
		comandos.put("recuperarSenha", new SenhaCommand());
		
		//COMANDOS DE USUARIO
		
		comandos.put("telaUser", new CreateScreenUserCommand());
		comandos.put("listUser", new ListUserCommand());
		comandos.put("addUser", new AddUserCommand());
		comandos.put("editUser", new EditUserCommand());
		comandos.put("removerUsuario", new RemoveUserCommand());
		
		// tela modelo
		comandos.put("modelo", new ModeloCommand());
		
		//Livros
		comandos.put("telaLivro", new TelaLivroCommand());
		comandos.put("listLivro", new ListLivroCommand());
		comandos.put("cadastraLivro", new AddLivroCommand());
		comandos.put("removerLivro", new RemoveLivroCommand());
		
		//Bloco
		comandos.put("telaBloco", new TelaBlocoCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String proxima = null;

		try {
			Command comando = verificarComando(acao);
			proxima = comando.execute(request);
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
			logger.debug("User: " +usuario.getUsuario() + " - comando " + comando.toString() + " acao: " +acao );
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
	
		LogParametrosSession.logParametros(request);
		Object isJSON = request.getAttribute("JSON");
		if ( isJSON != null ) {
			response.setContentType("application/json");
			response.getWriter().print(proxima);
		}
		else {
			request.getRequestDispatcher(proxima).forward(request, response);
		}
	}

	private Command verificarComando(String acao) {
		Command comando = null;
		for (String key : comandos.keySet()) {
			if (key.equalsIgnoreCase(acao)) {
				comando = comandos.get(key);
			}
		}
		return comando;
	}
}

