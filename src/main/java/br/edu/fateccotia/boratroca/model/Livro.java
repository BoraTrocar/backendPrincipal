package br.edu.fateccotia.boratroca.model;

import jakarta.persistence.*;
import lombok.Data;

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

	@Column(name = "imgCapa", columnDefinition = "LONGBLOB")
	private byte[] img;
	
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

	public void setImg(String img) {
		this.img = img.getBytes();
	}
}
