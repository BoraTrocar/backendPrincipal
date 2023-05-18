package br.edu.fateccotia.boratroca.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;

	public Optional<Autor> findByAutor(String autor) {
		
		return autorRepository.findByAutor(autor);
	}
	
	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}

}
