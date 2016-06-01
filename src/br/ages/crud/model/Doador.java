package br.ages.crud.model;

public class Doador {
	private String nome;
	private String cpf;
	private String email;
	
	public Doador(){
		nome = null;
		cpf = null;
		email = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return "== Informações do Doador ==\nNome: " + nome + "\nCPF: " + cpf + "\nE-mail: " + email;
	}
}
