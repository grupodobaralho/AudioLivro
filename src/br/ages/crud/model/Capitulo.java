package br.ages.crud.model;

import java.util.ArrayList;
import java.util.List;

public class Capitulo {
	private Integer idCapitulo;
	private String nome;
	private int numero;
	private Livro livro;
	private List<Bloco> blocos;
	
	public Capitulo() {}
	
	public Capitulo(String nome, int numero, Livro livro){
		this.nome = nome;
		this.numero = numero;
		this.setLivro(livro);
		blocos = new ArrayList<Bloco>();
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

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public String toString() {
		return "== Informa��es do Cap�tulo ==\nNome: " + nome + "\nN�mero: " + numero;
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
	
	
}
