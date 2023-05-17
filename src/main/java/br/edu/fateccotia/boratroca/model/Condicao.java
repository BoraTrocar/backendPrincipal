package br.edu.fateccotia.boratroca.model;

import java.util.List;

import br.edu.fateccotia.boratroca.enums.CondicaoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Condicao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int idCondicao;
	private CondicaoEnum condicao;
	@OneToMany
	private List<Livro> livro;
	
	public Condicao(String condicao) {
		this.condicao = CondicaoEnum.valueOf(condicao);
	}
	

	public Condicao() {
		
	}

	public int getIdCondicao() {
		return idCondicao;
	}

	public void setIdCondicao(int idCondicao) {
		this.idCondicao = idCondicao;
	}

	public CondicaoEnum getCondicao() {
		return condicao;
	}

	public void setCondicao(CondicaoEnum condicao) {
		this.condicao = condicao;
	}

	public List<Livro> getLivro() {
		return livro;
	}

	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}
	
	
	
}
