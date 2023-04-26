package br.edu.fateccotia.boratroca.model;

import br.edu.fateccotia.boratroca.enums.CondicaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Condicao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int idCondicao;
	private CondicaoEnum condicaoEnum;
	
	public Condicao(int idCondicao, CondicaoEnum condicaoEnum) {
		super();
		this.idCondicao = idCondicao;
		this.condicaoEnum = condicaoEnum;
	}
	
	public Condicao() {
		
	}

	public int getIdCondicao() {
		return idCondicao;
	}

	public void setIdCondicao(int idCondicao) {
		this.idCondicao = idCondicao;
	}

	public CondicaoEnum getCondicaoEnum() {
		return condicaoEnum;
	}

	public void setCondicaoEnum(CondicaoEnum condicaoEnum) {
		this.condicaoEnum = condicaoEnum;
	}
	
	
}
