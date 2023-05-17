package br.edu.fateccotia.boratroca.dto;

import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.model.Categoria;
import br.edu.fateccotia.boratroca.model.Condicao;
import br.edu.fateccotia.boratroca.model.Usuario;

public class LivroDTO {
	
	private String isbn, nomeLivro;
	private Autor autor; 
	private Categoria categoria;
	private Condicao condicao;
	private Usuario usuario;
}
