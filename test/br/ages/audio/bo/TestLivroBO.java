package br.ages.audio.bo;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.dao.LivroDAO;

@RunWith(MockitoJUnitRunner.class)
public class TestLivroBO {

	@Mock
	private LivroDAO livroMockDAO;
	private LivroBO livroBO;
	
	@Test
	public void testVerificaCamposNull() {
		fail("Not yet implemented");
	}

}
