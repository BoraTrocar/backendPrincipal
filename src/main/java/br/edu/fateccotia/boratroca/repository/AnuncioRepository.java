package br.edu.fateccotia.boratroca.repository;

import br.edu.fateccotia.boratroca.model.Livro;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


public interface AnuncioRepository extends PagingAndSortingRepository <Livro, Integer> {

}
