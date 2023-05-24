package br.edu.fateccotia.boratroca.service;

import java.util.List;
import java.util.Optional;

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

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	public Optional<Livro> findByIdLivro(int id) {
		return livroRepository.findByIdLivro(id);
	}
	
}
