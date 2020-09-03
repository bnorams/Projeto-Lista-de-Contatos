package com.algaworks;

public class Contato {
	
	private String id;
	private String nome;
	private String telefone;
	
	public Contato(){}; //Método construtor. Será usado para popular a lista de contatos que serão passados pelo formulário.
	
	public Contato(String id, String nome, String telefone) { //Será usado para popular a lista de contatos diretamente ao iniciar a aplicação.
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	
	
	public boolean isNovo() { //Verifica se o ID é Null, se for null é pq ele ainda não existe 
		return id==null;	  //Ai ele é considerado novo. Se for false, é um registro que já existe.
		
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
