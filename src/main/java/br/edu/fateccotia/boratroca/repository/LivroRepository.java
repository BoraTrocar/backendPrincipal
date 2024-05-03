package br.edu.fateccotia.boratroca.repository;

import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.model.Categoria;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{

	Optional<Livro> findByIdLivro(int id);
	Livro deleteById(int id);
	List<Livro> findAllByUsuario(Usuario usuario);

	@Query("SELECT l FROM Livro l " +
			"WHERE l.nomeLivro LIKE %:parametro% " +
			"OR l.descricao LIKE %:parametro% " +
			"OR l.autor.nomeAutor LIKE %:parametro% " +
			"OR l.categoria.nomeCategoria LIKE %:parametro%")
	Optional<List<Livro>> findAllByNomeLivroOrDescricaoOrAutorNomeOrCategoriaNomeLike(@Param("parametro") String parametro);
}
