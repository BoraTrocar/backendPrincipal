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
@Table(name = "autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true)
	private int idAutor;
	
	@Column(unique=true)
	private String autor;
	
	@OneToMany
	private List<Livro> livro;
	
	public Autor(String autor) {
		this.autor = autor;
	}
	
	
	public Autor() {
		
	}


	public int getIdAutor() {
		return idAutor;
	}


	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String nomeAutor) {
		this.autor = nomeAutor;
	}


	public List<Livro> getLivro() {
		return livro;
	}


	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}
	
	
	
}
