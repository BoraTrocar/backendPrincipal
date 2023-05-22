package br.edu.fateccotia.boratroca.model;

import java.util.ArrayList;
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
@Table(name = "autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true)
	private int idAutor;
	
	@Column(unique=true)
	private String nomeAutor;
	
	@OneToMany
	@JoinColumn(name = "idAutor")
	private List<Livro> livro = new ArrayList<>();
	
	public Autor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	
	public Autor() {
		
	}


	public int getIdAutor() {
		return idAutor;
	}


	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}


	public String getNomeAutor() {
		return nomeAutor;
	}


	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}


	public List<Livro> getLivro() {
		return livro;
	}


	public void setLivro(Livro livro) {
		this.livro.add(livro);
	}
	
	
	
}
