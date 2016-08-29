package br.ages.audio.bo;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.dao.LivroDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;
import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TestLivroBO extends TestCase {
	
	private LivroBO livroBO;
	private Livro livro;

	@Mock
	LivroDAO livroMock;
	
	@Before
	public void LivroBOTest(){
		livroBO = new LivroBO();
		livroBO.setLivroDAO(livroMock);
		livro = new Livro();
		livro.setISBN("123456789");
	}
	
	@Test
	public void testAtualizarLivroCorreto() throws PersistenciaException, SQLException {
		
		Mockito.when(livroMock.atualizarLivro(livro)).thenReturn(true);
		boolean condition = livroBO.atualizarLivro(livro);
		assertTrue("OK", condition);
		boolean teste = livroBO.atualizarLivro(livro);
		assertTrue(teste);
	}
		
	@Test(expected=Exception.class)
	public void testaExcecao() throws Exception{
		/* teste que espera o disparo de uma exceção */
		
	}
		
	
	
		
	

	@Test
	public void testBuscarLivro() {
		
	}

	@Test
	public void testListarLivros() {
		fail("Not yet implemented");
	}

}
