package br.edu.fateccotia.boratroca.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int idCategoria;
	@Column(unique=true)
	private String nomeCategoria;
	@OneToMany
	@JoinColumn(name = "idCategoria")
	private List<Livro> livro;
	
	
	
	public Categoria() {
		
	}
	
	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
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
	public List<Livro> getLivro() {
		return livro;
	}
	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}
	
	
	
	
}
