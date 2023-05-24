package br.edu.fateccotia.boratroca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "condicao")

public class Condicao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int idCondicao;
	
	private String nomeCondicao;


	public Condicao(String nomeCondicao) {
		this.nomeCondicao = nomeCondicao;
	}

	public Condicao() {
		
	}

	public int getIdCondicao() {
		return idCondicao;
	}

	public void setIdCondicao(int idCondicao) {
		this.idCondicao = idCondicao;
	}

	public String getNomeCondicao() {
		return nomeCondicao;
	}

	public void setNomeCondicao(String nomeCondicao) {
		this.nomeCondicao = nomeCondicao;
	}	
}
