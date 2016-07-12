package br.ages.audio.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.ages.crud.dao.CapituloDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;

public class CapituloBO {
	
	private CapituloDAO capituloDAO;

	public CapituloBO() {
		capituloDAO = new CapituloDAO();
	}
	
	private boolean crudCapitulos(Capitulo[] capitulos, Livro livro, int operation) {
		boolean crudReturn = true;
		for (int i = 0; i < capitulos.length; i++) {
			Capitulo capitulo = capitulos[i];
			capitulo.setLivro(livro);
			
			try {
				switch (operation) {
				case 1:
					crudReturn = capituloDAO.cadastrarCapitulo(capitulo);
					break;
				case 2:
					crudReturn = capituloDAO.atualizarCapitulo(capitulo);
					break;
				case 3:
					crudReturn = capituloDAO.deletarCapitulo(capitulo);
					break;
				default:
					break;
				}
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if ( !crudReturn ) {
				break;
			}
		}
		return crudReturn;
	}
	
	public boolean cadastrarCapitulos(Capitulo[] capitulosToInsert, Livro livro) {
		return crudCapitulos(capitulosToInsert, livro, 1);
	}
	
	public boolean atualizarCapitulos(Capitulo[] capitulosToUpdate, Livro livro) {
		return crudCapitulos(capitulosToUpdate, livro, 2);
	}
	
	public boolean deletarCapitulos(Capitulo[] capitulosToDelete, Livro livro) {
		return crudCapitulos(capitulosToDelete, livro, 3);
	}
	
	public boolean processarCapitulos(Capitulo[] capitulosToUpsert, Livro livro) {
		boolean updateReturn = false;
		try {
			// Busca todos capítulos do livro para efetuar as comparações
			ArrayList<Capitulo> capitulos = buscarCapitulosDoLivro(livro);
			
			// Processa o que é para deletar e deleta
			ArrayList<Capitulo> capitulosToDelete = getLivrosToDelete(capitulos, capitulosToUpsert);
			if ( capitulosToDelete.size() > 0 ) {
				updateReturn = deletarCapitulos(capitulosToDelete.toArray(new Capitulo[capitulosToDelete.size()]), livro);
			}
			
			// Processa o que é para atualizar e atualiza
			ArrayList<Capitulo> capitulosToUpdate = getLivrosToUpdate(capitulos, capitulosToUpsert);
			if ( capitulosToUpdate.size() > 0 ) {
				updateReturn = atualizarCapitulos(capitulosToUpdate.toArray(new Capitulo[capitulosToUpdate.size()]), livro);
			}
			
			// Processa o que é para inserir e insere
			ArrayList<Capitulo> capitulosToInsert = getLivrosToInsert(capitulos, capitulosToUpsert);
			if ( capitulosToInsert.size() > 0 ) {
				updateReturn = cadastrarCapitulos(capitulosToInsert.toArray(new Capitulo[capitulosToInsert.size()]), livro);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return updateReturn;
	}
	
	private ArrayList<Capitulo> getLivrosToDelete(ArrayList<Capitulo> capitulos, Capitulo[] capitulosToUpsert) {
		ArrayList<Capitulo> capitulosReturn = new ArrayList<Capitulo>();
		
		for ( int i = 0; i < capitulos.size(); i++ ) {
			Capitulo capitulo = capitulos.get(i);
			boolean toDelete = true;
			
			for ( int j = 0; j < capitulosToUpsert.length; j++ ) {
				Capitulo capituloToUpsert = capitulosToUpsert[j];
				if ( capituloToUpsert.getIdCapitulo() > 0 && capituloToUpsert.getIdCapitulo() == capitulo.getIdCapitulo() ) {
					toDelete = false;
					break;
				}
			}
			
			if ( toDelete ) {
				capitulosReturn.add(capitulo);
			}
		}
		return capitulosReturn;
	}
	
	private ArrayList<Capitulo> getLivrosToUpdate(ArrayList<Capitulo> capitulos, Capitulo[] capitulosToUpsert) {
		ArrayList<Capitulo> capitulosReturn = new ArrayList<Capitulo>();
		
		if (capitulosToUpsert.length > 0) {
			for ( int i = 0; i < capitulos.size(); i++ ) {
				Capitulo capitulo = capitulos.get(i);
				boolean toDelete = true;
				
				for ( int j = 0; j < capitulosToUpsert.length; j++ ) {
					Capitulo capituloToUpsert = capitulosToUpsert[j];
					if ( capituloToUpsert.getIdCapitulo() > 0 && capituloToUpsert.getIdCapitulo() == capitulo.getIdCapitulo() ) {
						toDelete = false;
						break;
					}
				}
				
				if ( toDelete ) {
					capitulosReturn.add(capitulo);
				}
			}
		}
		return capitulosReturn;
	}
	
	private ArrayList<Capitulo> getLivrosToInsert(ArrayList<Capitulo> capitulos, Capitulo[] capitulosToUpsert) {
		
		ArrayList<Capitulo> capitulosReturn = new ArrayList<Capitulo>();
		
		for ( int i = 0; i < capitulos.size(); i++ ) {
			Capitulo capitulo = capitulos.get(i);
			boolean toDelete = true;
			
			for ( int j = 0; j < capitulosToUpsert.length; j++ ) {
				Capitulo capituloToUpsert = capitulosToUpsert[j];
				if ( capituloToUpsert.getIdCapitulo() > 0 && capituloToUpsert.getIdCapitulo() == capitulo.getIdCapitulo() ) {
					toDelete = false;
					break;
				}
			}
			
			if ( toDelete ) {
				capitulosReturn.add(capitulo);
			}
		}
		return capitulosReturn;
	}
	
	public ArrayList<Capitulo> buscarCapitulosDoLivro(Livro livro) throws PersistenciaException, SQLException {
		return capituloDAO.buscarCapitulosDoLivro(livro);
	}
}
