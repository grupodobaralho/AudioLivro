package br.ages.audio.bo;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.dao.BlocoDAO;
import br.ages.crud.dao.LivroDAO;

@RunWith(MockitoJUnitRunner.class)
public class TestBlocoBO {
	
	@Mock
	private BlocoDAO blocoMockDAO;
	private BlocoBO blocoBO;
	
	@Test
	public void testVerificaCamposNull() {
		fail("Not yet implemented");
	}
	
}

