package br.ages.crud.model;

import java.util.ArrayList;
import java.util.List;

public class Capitulo {
	private Integer idCapitulo;
	private String nome;
	private int numero;
	private Livro livro;
	private ArrayList<Bloco> blocos;
	private StatusCapituloEnum statusCapitulo;
	
	public Capitulo() {}
	

	public Capitulo(String nome, int numero, Livro livro, StatusCapituloEnum s){
		this.nome = nome;
		this.numero = numero;
		this.setLivro(livro);
		blocos = new ArrayList<Bloco>();
		statusCapitulo = s;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ArrayList<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(ArrayList<Bloco> blocos) {
		this.blocos = blocos;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getIdCapitulo() {
		return idCapitulo;
	}

	public void setIdCapitulo(Integer idCapitulo) {
		this.idCapitulo = idCapitulo;
	}
	
	public StatusCapituloEnum getStatusCapitulo(){
		return statusCapitulo;
	}
	
	public void setStatusCapitulo(StatusCapituloEnum s){
		this.statusCapitulo = s;		
	}
	
	public String toString() {
		return "== Informações do capítulo ==\nNome: " + nome + "\nNúmero: " + numero +
				"\nStatus do Capítulo: " + statusCapitulo;
	}	
}
