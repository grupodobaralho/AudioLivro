package br.ages.audio.bo;

import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Livro;

import org.junit.Before;
import org.junit.Test;

public class LivroBOTest {
	
	private LivroBO livroBO;		

	@Before
	public void Create(){
		livroBO = new LivroBO();
	}	

	@Test
	public void testLivroBO() {
			
	}

	@Test
	public void testCadastrarLivro() {
		
		
		
	}

	@Test
	public void testAtualizarLivro() {
		
		boolean thrown = false;		

		  try {
		    livroBO.atualizarLivro(null);
		  } catch (PersistenciaException e) {
		    thrown = true;
		  }
		  try {
			    livroBO.atualizarLivro(null);
		}catch (SQLException a){
		  		thrown = true;
		  	}

		  assertTrue(thrown);
		
	}

	@Test
	public void testBuscarLivro() {
		
	}

	@Test
	public void testListarLivros() {
		fail("Not yet implemented");
	}

}
