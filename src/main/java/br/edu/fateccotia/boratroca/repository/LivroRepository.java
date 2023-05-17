package br.edu.fateccotia.boratroca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
}
