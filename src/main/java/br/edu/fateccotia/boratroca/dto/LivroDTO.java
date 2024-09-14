package br.edu.fateccotia.boratroca.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LivroDTO {
	private int idLivro;

	@NotBlank
	private String nomeLivro;

	private String isbn;

	private String descricao;

	private MultipartFile imagemFile;

	private byte[] imagemBlob;

	@NotBlank(message = "Condição não pode ser vázia")
	private String condicao;

	@NotBlank
	private String categoria;

	@NotBlank
	private String autor;

	@NotBlank
	private String usuario;
}
