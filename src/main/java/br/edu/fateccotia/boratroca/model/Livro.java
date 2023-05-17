package br.edu.fateccotia.boratroca.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")

public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true)
	private int idLivro;
	private String nomeLivro;
	private String isbn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCondicao")
	private Condicao condicao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAutor")
	private Autor autor;
	
	
	public Livro() {
		
	}
	
	
	public Livro(int idLivro, String nomeLivro, String isbn, Usuario usuario, Condicao condicao, Categoria categoria,
			Autor autor) {
		super();
		this.idLivro = idLivro;
		this.nomeLivro = nomeLivro;
		this.isbn = isbn;
		this.usuario = usuario;
		this.condicao = condicao;
		this.categoria = categoria;
		this.autor = autor;
	}


	public int getIdLivro() {
		return idLivro;
	}


	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}


	public String getNomeLivro() {
		return nomeLivro;
	}


	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Condicao getCondicao() {
		return condicao;
	}


	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
	
	
	
	
}
