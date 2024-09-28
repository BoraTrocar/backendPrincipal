package br.edu.fateccotia.boratroca.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LivroDTO {
	private int idLivro;

	@NotBlank
	private String nomeLivro;

	@Min(6) @Max(13)
	private String isbn;

	private String descricao;

	private String imagem;

	@NotBlank(message = "Condição não pode ser vázia")
	private String condicao;

	@NotBlank
	private String categoria;

	@NotBlank
	private String autor;

	@NotBlank
	private String usuario;
}
