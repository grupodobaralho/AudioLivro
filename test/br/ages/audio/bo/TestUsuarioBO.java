package br.ages.audio.bo;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import br.ages.crud.model.Usuario;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TestUsuarioBO extends TestCase{
	
	private UsuarioBO usuarioBO;
	private Usuario usuario;
	
	
	@Mock
	UsuarioDAO usuarioMock;
	List<Usuario> listaMock;
	
	@Before
	public void  init(){
		usuarioBO = new UsuarioBO();
		usuarioBO.setUsuarioDAO(usuarioMock);
		usuario = new Usuario();
		
	}

	@Test
	public void testUsuarioBOCorreto() throws PersistenciaException {
		usuario.setUsuario("Test");
		usuario.setSenha("123abc");
		Mockito.when(usuarioMock.validarUsuario(usuario)).thenReturn(usuario);
		boolean condition = usuarioBO.validaUsuarioResponsavel("Test","123abc");
		assertTrue(condition);	
	}
	
	@Test
	public void testUsuarioBOIncorreto() throws PersistenciaException {		
		Mockito.when(usuarioMock.validarUsuario(usuario)).thenReturn(null);
		boolean condition = usuarioBO.validaUsuarioResponsavel("Test","123abc");
		assertFalse(condition);	
	}

	@Test
	public void testValidaUsuarioResponsavel() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidaUsuarioA() {
		
	}

	@Test
	public void testCadastraUsuarioCorreto() throws NegocioException, SQLException, ParseException, PersistenciaException {
		usuario.setIdUsuario(1234567);
		Mockito.when(usuarioMock.cadastrarUsuario(usuario)).thenReturn(1234567);
		int result = usuarioBO.cadastraUsuario(usuario);
		assertEquals(result, 1234567);
		
	}

	@Test
	public void testListarUsuarioAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarUsuarioCorreto() throws NegocioException, PersistenciaException, SQLException{
		Mockito.when(usuarioMock.listarUsuarios()).thenReturn(listaMock);
		List<Usuario> condition = usuarioMock.listarUsuarios();
		assertEquals(condition, listaMock);
	}
	
	
	@Test
	public void testRemoverUsuario() {
		
	}

	@Test
	public void testConsultaTipoUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscaUsuarioId() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaTipoUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaUsuariosReponsaveis() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscaSenha() {
		fail("Not yet implemented");
	}

}
