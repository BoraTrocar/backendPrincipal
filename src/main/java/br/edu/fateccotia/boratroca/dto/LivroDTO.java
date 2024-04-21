package br.edu.fateccotia.boratroca.dto;


import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.model.Categoria;
import br.edu.fateccotia.boratroca.model.Condicao;
import br.edu.fateccotia.boratroca.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LivroDTO {
	private int idLivro;
	private String nomeLivro;
	private String isbn;
	private String descricao;
	private MultipartFile img;
	private Usuario usuario;
	private Condicao condicao;
	private Categoria categoria;
	private Autor autor;
}
