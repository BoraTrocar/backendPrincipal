package br.edu.fateccotia.boratroca.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.fateccotia.boratroca.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
	Optional<Autor> findByNomeAutor(String autor);

	List<Autor> findAllByNomeAutorLike(String nomeAutor);
}
