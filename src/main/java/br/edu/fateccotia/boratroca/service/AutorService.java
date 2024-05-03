package br.edu.fateccotia.boratroca.service;

import java.util.List;
import java.util.Optional;
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
		return autorRepository.save(autor);
	}

	public List<Autor> findAllByNomeAutorLike(String nomeAutor) {
		return autorRepository.findAllByNomeAutorLike(nomeAutor);
	}

}
