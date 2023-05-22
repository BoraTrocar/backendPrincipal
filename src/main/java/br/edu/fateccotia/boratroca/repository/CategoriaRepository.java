package br.edu.fateccotia.boratroca.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.fateccotia.boratroca.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	Optional<Categoria> findByNomeCategoria(String categoria);
	
}
