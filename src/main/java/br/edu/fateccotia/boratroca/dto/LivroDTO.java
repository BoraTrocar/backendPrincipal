package br.edu.fateccotia.boratroca.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LivroDTO {
	private int idLivro;
	private String nomeLivro;
	private String isbn;
	private String descricao;
	private MultipartFile imagemFile;
	private byte[] imagemBlob;
	private String condicao;
	private String categoria;
	private String autor;

	LivroDTO() {

	}

	public LivroDTO(int idLivro, String nomeLivro, String isbn, String descricao, MultipartFile imagemFile, byte[] imagemBlob, String condicao, String categoria, String autor) {
		this.idLivro = idLivro;
		this.nomeLivro = nomeLivro;
		this.isbn = isbn;
		this.descricao = descricao;
		this.imagemFile = imagemFile;
		this.imagemBlob = imagemBlob;
		this.condicao = condicao;
		this.categoria = categoria;
		this.autor = autor;
	}
}
