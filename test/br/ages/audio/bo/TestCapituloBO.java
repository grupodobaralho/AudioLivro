package br.ages.audio.bo;

import static org.junit.Assert.assertEquals;

import java.awt.List;
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
	CapituloBO capituloBOMock;
	
	@Before
	public void init() {

		capituloBO = new CapituloBO();
		livro = new Livro();
		capitulo = new Capitulo();
		capituloBO.setCapituloDAO(capituloMock);
		livro.setIdLivro(1);
		capitulo.setIdCapitulo(10);
		capitulo.setLivro(livro);
		capituloBOMock = new CapituloBO();

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
		Mockito.when(capituloMock.cadastrarCapitulo(capitulo)).thenReturn(true);
		boolean condition = capituloBO.cadastrarCapitulos(capitulosVet, livro);
		assertEquals(condition, true);

	}

	@Test
	public void testAtualizarCadastrarCapitulosCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		capitulosVet[0] = capitulo;
		Mockito.when(capituloMock.atualizarCapitulo(capitulo)).thenReturn(true);
		boolean condition = capituloBO.atualizarCapitulos(capitulosVet, livro);
		assertEquals(condition, true);
	}
	
	@Test
	public void testDeletarCapitulosCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		//List capituloLis = new List();
		//livro.setCapitulos(capituloLis);
		capitulosVet[0] = capitulo;
		Mockito.when(capituloMock.deletarCapitulo(capitulo)).thenReturn(true);
		boolean condition = capituloBO.deletarCapitulos(capitulosVet, livro);
		assertEquals(condition,true);

	}

	@Test
	public void testProcessarCapitulosCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		boolean condition = capituloBO.processarCapitulos(capitulosVet, livro);
		assertEquals(condition,false);
	}
	
	//Nos casos de teste a seguir a lógica utilizada foi a seguinte:
	//1- O método recebe 1 array de capitulo e 1 vetor de capitulo.
	//2- Ele verifica se existe alguma diferença entre esses dois
	//3- Se existir ele adiciona em um array de capitulos e retorna
	//COMO NESTES CASOS NÃO EXISTE DIFERENÇA então nada é feito e ele retorna um array vazio.
	@Test
	public void testGetLivrosToDeleteCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		ArrayList<Capitulo> capitulosLis = new ArrayList<>();
		ArrayList<Capitulo> capitulosLis2 = new ArrayList<>();
		ArrayList<Capitulo> condition = capituloBO.getLivrosToDelete(capitulosLis, capitulosVet);
		assertEquals(condition,capitulosLis2);
		}
	
	@Test
	public void testGetLivrosToUpdateCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		ArrayList<Capitulo> capitulosLis = new ArrayList<>();
		ArrayList<Capitulo> capitulosLis2 = new ArrayList<>();
		ArrayList<Capitulo> condition = capituloBO.getLivrosToUpdate(capitulosLis, capitulosVet);
		assertEquals(condition,capitulosLis2);
		}
	
	@Test
	public void testGetLivrosToInsertCorreto() throws PersistenciaException, SQLException {
		Capitulo[] capitulosVet = new Capitulo[1];
		ArrayList<Capitulo> capitulosLis = new ArrayList<>();
		ArrayList<Capitulo> capitulosLis2 = new ArrayList<>(); 
		ArrayList<Capitulo> condition = capituloBO.getLivrosToInsert(capitulosLis, capitulosVet);
		assertEquals(condition,capitulosLis2);
		}
}
