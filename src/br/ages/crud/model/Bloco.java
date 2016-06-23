package br.ages.crud.model;

public class Bloco {
	private String lcl_conteudo;
	private String lcl_arq_audio;
	private Status statusBloco;
	
	
	public Bloco(String lclC, String lclArq, Status x){
		lcl_conteudo = lclC;
		lcl_arq_audio = lclArq;
		statusBloco = x;
	}

	public String getLcl_conteudo() {
		return lcl_conteudo;
	}

	public void setLcl_conteudo(String lcl_conteudo) {
		this.lcl_conteudo = lcl_conteudo;
	}

	public String getLcl_arq_audio() {
		return lcl_arq_audio;
	}

	public void setLcl_arq_audio(String lcl_arq_audio) {
		this.lcl_arq_audio = lcl_arq_audio;
	}

	public Status getStatusBloco() {
		return statusBloco;
	}

	public void setStatusBloco(Status statusBloco) {
		this.statusBloco = statusBloco;
	}
	

	
	@Override
	public String toString() {
		return "== Informações do Bloco ==\nLocal do conteúdo: " + lcl_conteudo + 
				"\nLocal do arquivo áudio: " + lcl_arq_audio + "\nStatus do Bloco: "
				+ statusBloco;
	}
	
}
