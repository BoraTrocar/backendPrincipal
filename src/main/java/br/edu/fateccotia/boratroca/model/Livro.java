package br.edu.fateccotia.boratroca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@ManyToOne
	private Usuario usuario_FK;
	@ManyToOne
	private Condicao condicao_FK;
	@ManyToOne
	private Categoria categoria_Fk;
	@ManyToOne
	private Autor autor_FK;
}
