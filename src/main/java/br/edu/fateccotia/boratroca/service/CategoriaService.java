package br.edu.fateccotia.boratroca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.model.Categoria;
import br.edu.fateccotia.boratroca.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> findByCategoria(String categoria) {
		return categoriaRepository.findByCategoria(categoria);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
}
