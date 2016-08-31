package br.ages.audio.bo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.dao.CapituloBlocoDAO;
import br.ages.crud.model.Bloco;

public class TestBlocoBO {
	
	private BlocoBO blocoBO;
	private Bloco blocos;
	
	 @Mock
	 BlocoDAO bloco;
	 CapituloBlocoDAO capitulo;
	 
	 @Before
	 public void TestBlocoBO(){
		blocoBO = new BlocoBO(); 
		blocos = new Bloco();
		 
	      
	 }
	 
	 
	 @Test
	 public void testCadastraBloco(){
	 

	 }
	 
	 @Test
	 public void testAlteraCaminho(){
		 
 	}
}