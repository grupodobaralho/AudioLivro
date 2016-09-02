package br.ages.audio.bo;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.dao.CapituloBlocoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.Status;
@RunWith(MockitoJUnitRunner.class)
public class TestBlocoBO {
	
	private BlocoBO blocoBO;
	private Bloco bloco;
	
	 @Mock
	 BlocoDAO blocoDAO;
	 CapituloBlocoDAO capituloDAO;
	 
	 @Before
	 public void TestBlocoBOTest(){
		 
		 bloco = new Bloco("conteudo","audio",Status.APROVADO);
		 bloco.setId_bloco(20);
	 }
	 
	 
	 @Test
	 public void testCadastraBloco() throws PersistenciaException, NegocioException, SQLException{
	 
		 Mockito.when(blocoDAO.cadastraBloco(bloco)).thenReturn(20);   
		 int condition = blocoBO.cadastraBloco(bloco, 30);
		 assertEquals(20,condition);
	 }
	 
	 @Test
	 public void testAlteraCaminho(){
		 
 	}
}