package br.ages.audio.bo;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.dao.CapituloDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;

@RunWith(MockitoJUnitRunner.class)
public class TestCapituloBO {
	private CapituloBO capituloBO;
	private Capitulo capitulo;
	private Livro livro;

	@Mock
	CapituloDAO capituloMock;

	@Before
	public void init() {

		capituloBO = new CapituloBO();
		livro = new Livro();
		capitulo = new Capitulo();
		capituloBO.setCapituloDAO(capituloMock);
		livro.setIdLivro(1);
		capitulo.setIdCapitulo(10);
		capitulo.setLivro(livro);

	}

	@Test
	public void testBuscarCapitulosDoLivroCorreto() throws PersistenciaException, SQLException {
		ArrayList<Capitulo> capitulos = new ArrayList<>();
		Mockito.when(capituloMock.buscarCapitulosDoLivro(livro)).thenReturn(capitulos);
		ArrayList<Capitulo> condition = capituloBO.buscarCapitulosDoLivro(livro);
		assertEquals(condition, capitulos);
	}
	
	@Test
	public void testBuscarCapitulosDoLivroErrado() throws PersistenciaException, SQLException {
		ArrayList<Capitulo> capitulos = new ArrayList<>();
		Mockito.when(capituloMock.buscarCapitulosDoLivro(null)).thenReturn(capitulos);
		ArrayList<Capitulo> condition = capituloBO.buscarCapitulosDoLivro(null);
		assertEquals(condition, capitulos);
	}

	@Test
	public void testCadastrarCapitulosCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		capitulosVet[0] = capitulo;
		boolean retorno = true;
		Mockito.when(capituloMock.cadastrarCapitulo(capitulo)).thenReturn(true);
		boolean condition = capituloBO.cadastrarCapitulos(capitulosVet, livro);
		assertEquals(condition, retorno);

	}

	@Test
	public void testAtualizarCadastrarCapitulosCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		capitulosVet[0] = capitulo;
		boolean retorno = true;
		Mockito.when(capituloMock.atualizarCapitulo(capitulo)).thenReturn(true);
		boolean condition = capituloBO.atualizarCapitulos(capitulosVet, livro);
		assertEquals(condition, retorno);
	}
	
	@Test
	public void testDeletarCapitulosCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		capitulosVet[0] = capitulo;
		boolean retorno = true;
		Mockito.when(capituloMock.deletarCapitulo(capitulo)).thenReturn(true);
		boolean condition = capituloBO.deletarCapitulos(capitulosVet, livro);
		assertEquals(condition, retorno);

	}
}