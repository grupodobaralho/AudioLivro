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
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TestLivroBO extends TestCase {
	
	private LivroBO livroBO;
	private Livro livro;
	private Livro livroRuim;

	@Mock
	LivroDAO livroMock;
	List<Livro> listaMock;
		
	@Before
	public void init(){
		livroBO = new LivroBO();
		livroBO.setLivroDAO(livroMock);
		livro = new Livro();
		livro.setISBN("123456789");
		livro.setIdLivro(123);
		livroRuim = new Livro(); //ruim == atributo ISBN não inicializado		
	}
	
	@Test
	public void testCadastrarLivroCorreto() throws PersistenciaException, NegocioException, SQLException{		
		Mockito.when(livroMock.cadastraLivro(livro)).thenReturn(123);
		int condition = livroBO.cadastrarLivro(livro);
		assertEquals(123,condition);			
	}
	
	@Test(expected=Exception.class)
	public void testCadastrarLivroIncorretoExcecao() throws Exception{
		livroBO.cadastrarLivro(null);
			
	}
	
	@Test
	public void testAtualizarLivroCorreto() throws PersistenciaException, SQLException, NegocioException {
		Livro livroDois = new Livro();
		livroDois.setIdLivro(123);
		livroDois.setISBN("987654321");
		Mockito.when(livroMock.cadastraLivro(livro)).thenReturn(123);
		Mockito.when(livroMock.atualizarLivro(livroDois)).thenReturn(true);
		Mockito.when(livroMock.buscarLivro(123)).thenReturn(livroDois);
		int idDoLivro = livroBO.cadastrarLivro(livro);		
		boolean condition = livroBO.atualizarLivro(livroDois);
		Livro retorno = livroBO.buscarLivro(idDoLivro);
		assertTrue(condition);
		assertEquals(retorno.getISBN(),"987654321");		
	}
	
	@Test
	public void testAtualizarLivroIncorreto() throws PersistenciaException, SQLException {
		Mockito.when(livroMock.atualizarLivro(null)).thenReturn(false);
		boolean condition = livroBO.atualizarLivro(null);
		assertFalse(condition);		
	 
	}
		
	@Test
	public void testValidaLivroCorreto(){
		boolean condition = livroBO.validaLivro(livro);
		assertTrue("OK", condition);
	}
	
	@Test
	public void testValidaLivroIncorreto(){
		boolean condition = livroBO.validaLivro(livroRuim);
		assertFalse(condition);
		condition =  livroBO.validaLivro(null);
		assertFalse(condition);
	}	
	
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
		Mockito.when(livroMock.listarLivros()).thenReturn(listaMock);
		List<Livro> condition = livroBO.listarLivros();
		assertEquals(condition,listaMock);
	}
}
