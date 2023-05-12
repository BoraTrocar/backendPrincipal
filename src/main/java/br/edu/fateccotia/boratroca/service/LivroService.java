package br.edu.fateccotia.boratroca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro save(Livro livro) {

		return livroRepository.save(livro);
	}
	
}
