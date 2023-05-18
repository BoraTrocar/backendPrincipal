package br.edu.fateccotia.boratroca.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "condicao")

public class Condicao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int idCondicao;
	
	@Column(name = "nomeCondicao")
	private String condicao;
	
	@OneToMany
	private List<Livro> livro;
	
	public Condicao(String condicao) {
		this.condicao = condicao;
	}
	

	public Condicao() {
		
	}

	public int getIdCondicao() {
		return idCondicao;
	}

	public void setIdCondicao(int idCondicao) {
		this.idCondicao = idCondicao;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public List<Livro> getLivro() {
		return livro;
	}

	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}
	
	
	
}
