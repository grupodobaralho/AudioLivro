package br.ages.crud.model;

import java.util.ArrayList;
import java.util.List;

public class Capitulo {
	private String nome;
	private int numero;
	private List<Bloco> blocos;
	
	public Capitulo(String nome, int numero){
		this.nome = nome;
		this.numero = numero;
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
		return "== Informações do Capítulo ==\nNome: " + nome + "\nNúmero: " + numero;
	}
	
	
}
