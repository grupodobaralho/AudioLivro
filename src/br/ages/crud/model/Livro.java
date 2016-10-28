package br.ages.crud.model;

import java.util.List;

public class Livro {
	private Integer idLivro;
	private String titulo;
	private String ISBN;
	private String autores;
	private List<Capitulo> capitulos;
	private StatusLivroEnum statusLivro;
	
	public Livro(){}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}
	
	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getAutores() {
		return autores;
	}
	
	public String toString() {
		return "== Informa��es do Livro ==\nTitulo: "+titulo+"\nISBN: "+ISBN+"\nAutores: "+autores;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}
	
	public StatusLivroEnum getStatus(){
		return statusLivro;
	}
	
	public void setStatus(StatusLivroEnum status){
		statusLivro = status;
	}	
	
}
