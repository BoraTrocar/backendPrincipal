package br.edu.fateccotia.boratroca.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int idCategoria;
	private String nomeCategoria;
	@OneToMany
	private List<Livro> livro;
	
	
	public Categoria(int idCategoria, String nomeCategoria) {
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}
	
	public Categoria() {
		
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
}
