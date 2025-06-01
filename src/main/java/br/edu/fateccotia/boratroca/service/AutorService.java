package br.edu.fateccotia.boratroca.service;

import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.exception.NotBlankException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;

	public Optional<Autor> findByNomeAutor(String autor) {
		return autorRepository.findByNomeAutor(autor);
	}
	
	public Autor save(Autor autor) {
		if(!autor.getNomeAutor().trim().isEmpty()) {
			return autorRepository.save(autor);
		} else {
			throw new NotBlankException("O campo autor não pode ser nulo");
		}
	}

	public List<Autor> findAllByNomeAutorLike(String nomeAutor) {
		return autorRepository.findAllByNomeAutorLike(nomeAutor);
	}

}
