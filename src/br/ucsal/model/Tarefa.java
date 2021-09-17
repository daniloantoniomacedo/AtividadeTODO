package br.ucsal.model;

public class Tarefa {
	
	private String titulo;
	private String descricao;
	private Situacao situacao;
		
	public Tarefa(String titulo, String descricao, Situacao situacao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.situacao = situacao;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Tarefa [titulo=" + titulo + ", descricao=" + descricao + ", situacao=" + situacao + "]";
	}
	
	
}
