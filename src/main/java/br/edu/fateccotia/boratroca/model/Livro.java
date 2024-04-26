package br.edu.fateccotia.boratroca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@Entity
@Table(name = "livro")

public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true)
	private int idLivro;
	private String nomeLivro;
	private String isbn;
	
	@Column(columnDefinition = "varchar(2000)")
	private String descricao;

	@JsonIgnore
	@Column(name = "imgCapa", columnDefinition = "LONGBLOB")
	private byte[] imagem;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	
	@ManyToOne
	@JoinColumn(name = "idCondicao")
	private Condicao condicao;
	
	
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	
	@ManyToOne
	@JoinColumn(name = "idAutor")
	private Autor autor;

	public Livro(String nomeLivro, String isbn, String descricao, MultipartFile imagem) throws IOException {
		this.nomeLivro = nomeLivro;
		this.isbn = isbn;
		this.descricao = descricao;
		this.imagem = imagem.getBytes();
	}

	public Livro(int idLivro, String nomeLivro, String isbn, String descricao, byte[] imagem, Usuario usuario, Condicao condicao, Categoria categoria, Autor autor) {
		this.idLivro = idLivro;
		this.nomeLivro = nomeLivro;
		this.isbn = isbn;
		this.descricao = descricao;
		this.imagem = imagem;
		this.usuario = usuario;
		this.condicao = condicao;
		this.categoria = categoria;
		this.autor = autor;
	}

	public Livro() {

	}
}
