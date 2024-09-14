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
}
