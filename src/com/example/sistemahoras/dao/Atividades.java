package com.example.sistemahoras.dao;

public class Atividades {

	private String descricao;
	private String date;
	
	public Atividades(){
		
	}

	public Atividades(String descricao,String date){
		this.descricao =descricao;
		this.date = date;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
