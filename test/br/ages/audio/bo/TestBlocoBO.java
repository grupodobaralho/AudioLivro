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
import br.ages.crud.model.StatusBlocoEnum;

@RunWith(MockitoJUnitRunner.class)
public class TestBlocoBO {

	private BlocoBO blocoBO;
	private Bloco bloco;

	@Mock
	BlocoDAO blocoDAOMock;
	@Mock
	CapituloBlocoDAO capituloBlocoDAOMock;

	@Before
	public void TestBlocoBOTest() {

		blocoBO = new BlocoBO();
		blocoBO.setBlocoDAO(blocoDAOMock);
		blocoBO.setCapituloBlocoDAO(capituloBlocoDAOMock);
		bloco = new Bloco("conteudo", "audio", StatusBlocoEnum.APROVADO);
	}

	@Test
	public void testCadastraBlocoCorreto() throws PersistenciaException, NegocioException, SQLException { 
		Mockito.when(blocoDAOMock.cadastraBloco(bloco)).thenReturn(30);		
		int condition = blocoBO.cadastraBloco(bloco, 1);
		assertEquals(30, condition);
	}

	 @Test 
	 public void testAlteraCaminho() throws PersistenciaException, NegocioException, SQLException {
		Mockito.when(blocoDAOMock.alteraCaminhoPdf("caminho", 20)).thenReturn(true);
		boolean condition = blocoBO.alteraCaminho("caminho", 20);
		assertTrue(condition);
	 	
	 }
	 
	 @Test
	 public void testExcluirBlocoCorreto() throws SQLException, NegocioException, SQLException{
		 Mockito.when(blocoDAOMock.excluirBloco(10)).thenReturn(true);
		 boolean condition = blocoBO.excluirBloco(10);
		 assertTrue(condition);		 
	 }
	 
}