package br.ages.crud.model;

public class Bloco {
	
	private Integer id_bloco;
	private String lcl_conteudo;
	private String lcl_arq_audio;
	private StatusBlocoEnum statusBloco;
		
	public Bloco(String lclC, String lclArq, StatusBlocoEnum x){
		lcl_conteudo = lclC;
		lcl_arq_audio = lclArq;
		statusBloco = x;
	}
	
	public Bloco() {
	}

	public Integer getId_bloco() {
		return id_bloco;
	}

	public void setId_bloco(Integer id_bloco) {
		this.id_bloco = id_bloco;
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

	public StatusBlocoEnum getStatusBloco() {
		return statusBloco;
	}

	public void setStatusBloco(StatusBlocoEnum statusBloco) {
		this.statusBloco = statusBloco;
	}
	
	@Override
	public String toString() {
		return "== Informa��es do Bloco ==\nLocal do conte�do: " + lcl_conteudo + 
				"\nLocal do arquivo �udio: " + lcl_arq_audio + "\nStatus do Bloco: "
				+ statusBloco;
	}
}
