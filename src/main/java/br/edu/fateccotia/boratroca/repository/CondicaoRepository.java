package br.edu.fateccotia.boratroca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.boratroca.model.Condicao;
@Repository
public interface CondicaoRepository extends JpaRepository<Condicao, Integer>{
	
	Optional<Condicao> findByCondicao(String Condicao);
}
