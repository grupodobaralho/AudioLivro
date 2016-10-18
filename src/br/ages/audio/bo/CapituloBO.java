package br.ages.audio.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.ages.crud.dao.CapituloDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Bloco;
import br.ages.crud.model.Capitulo;
import br.ages.crud.model.Livro;
import br.ages.crud.model.Status;

public class CapituloBO {
	
	private CapituloDAO capituloDAO;
	private BlocoBO blocoBO;
	
	public CapituloBO() {
		capituloDAO = new CapituloDAO();
	}
	public void setCapituloDAO(CapituloDAO capituloDAO){
		this.capituloDAO=capituloDAO;
	}
	
	/**
	 *Create, Read, Update, Delete 
	 *
	 */
	private boolean crudCapitulos(ArrayList<Capitulo> capitulos, Livro livro, int operation) {
		boolean crudReturn = true;
		for (Capitulo capitulo : capitulos) {			
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
	
	public boolean cadastrarCapitulos(ArrayList<Capitulo> capitulosToInsert, Livro livro) {
		return crudCapitulos(capitulosToInsert, livro, 1);
	}
	
	public boolean atualizarCapitulos(ArrayList<Capitulo> capitulosToUpdate, Livro livro) {
		return crudCapitulos(capitulosToUpdate, livro, 2);
	}
	
	public boolean deletarCapitulos(ArrayList<Capitulo> capitulosToDelete, Livro livro) {		
		ArrayList<Bloco> blocos;
		
		for (Capitulo capitulo : capitulosToDelete){
			
			blocos = capitulo.getBlocos();
			boolean blocoEmGravacao = false;
			
			for(int i = 0; i < blocos.size() && blocoEmGravacao == false; i++){
				
				if (blocos.get(i).getStatusBloco() == Status.EM_GRAVACAO){
					blocoEmGravacao = true;
				}
			}
			if(blocoEmGravacao==true)
				capitulosToDelete.remove(capitulo);
		}			
	
		if (capitulosToDelete.size() == 0) return false;
		else return crudCapitulos(capitulosToDelete, livro, 3);
	}
	
	public ArrayList<Capitulo> getLivrosToInsert(ArrayList<Capitulo> capitulos, Capitulo[] capitulosToUpsert) {
		
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
		//TODO verificar se o array é vazio; e se retornar outra coisa??
	}
	public Capitulo buscaCapitulo(Capitulo capituloAUX) throws PersistenciaException {
		return capituloDAO.buscaCapitulo(capituloAUX);
	}
	
	public boolean cadastrarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		return capituloDAO.cadastrarCapitulo(capitulo);
	}
	
	public boolean atualizarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		return capituloDAO.atualizarCapitulo(capitulo);
	}
	
	public boolean deletarCapitulo(Capitulo capitulo) throws PersistenciaException, SQLException {
		return capituloDAO.deletarCapitulo(capitulo);
	}
}
