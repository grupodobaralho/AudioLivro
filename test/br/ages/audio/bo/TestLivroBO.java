package br.ages.audio.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TestLivroBO extends TestCase {
	
	private LivroBO livroBO;
	private Livro livro;
	private Livro livroRuim;

	@Mock
	LivroDAO livroMock;
		
	@Before
	public void LivroBOTest(){
		livroBO = new LivroBO();
		livroBO.setLivroDAO(livroMock);
		livro = new Livro();
		livro.setISBN("123456789");
		livro.setIdLivro(123);
		livroRuim = new Livro();
		
		
	}
	
	@Test
	public void testCadastrarLivroCorreto() throws PersistenciaException, NegocioException, SQLException{		
		Mockito.when(livroMock.cadastraLivro(livro)).thenReturn(123);
		int condition = livroBO.cadastrarLivro(livro);
		assertEquals(123, condition);		
	}
	
	@Test
	public void testCadastrarLivroErrado() throws PersistenciaException, NegocioException, SQLException{
		Mockito.when(livroMock.cadastraLivro(null)).thenReturn(-1);
		int condition = livroBO.cadastrarLivro(null);
		assertEquals(-1, condition);	
	}
	
	@Test
	public void testAtualizarLivroCorreto() throws PersistenciaException, SQLException {
		
		Mockito.when(livroMock.atualizarLivro(livro)).thenReturn(true);
		boolean condition = livroBO.atualizarLivro(livro);
		assertTrue("OK", condition);
		boolean teste = livroBO.atualizarLivro(livro);
		assertTrue(teste);
	}
	
	@Test
	public void testAtualizarLivroIncorreto() throws PersistenciaException, SQLException {
		Mockito.when(livroMock.atualizarLivro(null)).thenReturn(false);
		boolean condition = livroBO.atualizarLivro(null);
		assertFalse("OK", condition);
		boolean teste = livroBO.atualizarLivro(null);
		assertFalse(teste);
	 
	}
		
	@Test
	public void testValidaLivroCorreto(){
		boolean condition = livroBO.validaLivro(livro);
		assertTrue("OK", condition);
	}
	
	@Test
	public void testValidaLivroIncorreto(){
		boolean condition = livroBO.validaLivro(livroRuim);
		assertFalse("OK", condition);
		boolean teste =  livroBO.validaLivro(null);
		assertFalse("OK", teste);
	}	
	
	/*
	 * @Test(expected=Exception.class)
	 
	public void testaExcecao() throws Exception{
		teste que espera o disparo de uma exceção
	
		
	}
	*/	
	
	@Test
	public void testBuscarLivroCorreto() throws PersistenciaException, SQLException {
		Mockito.when(livroMock.buscarLivro(123)).thenReturn(livro);	
		Livro umLivro = livroBO.buscarLivro(123);
		assertEquals(livro, umLivro);		
	}
	
	@Test
	public void testBuscarLivroIncorreto() throws PersistenciaException, SQLException {
		Mockito.when(livroMock.buscarLivro(-1)).thenReturn(null);	
		Livro umLivro = livroBO.buscarLivro(-1);
		assertEquals(null, umLivro);			
	}
	
	
	
	@Test
	public void testListarLivros() throws PersistenciaException, SQLException, NegocioException {
		ArrayList<Livro> listarLivros = new ArrayList<>();
		Mockito.when(livroMock.listarLivros()).thenReturn(listarLivros);
		List<Livro> condition = livroBO.listarLivros();
		assertEquals(condition,listarLivros);
	}
}
