package br.edu.fateccotia.boratroca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.model.Categoria;
import br.edu.fateccotia.boratroca.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> findByNomeCategoria(String categoria) {
		return categoriaRepository.findByNomeCategoria(categoria);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> findAllByNomeCategoriaLike(String nomeCategoria) {return categoriaRepository.findAllByNomeCategoriaLike(nomeCategoria);};
	
}
