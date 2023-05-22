package br.edu.fateccotia.boratroca.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.boratroca.model.Condicao;
import br.edu.fateccotia.boratroca.repository.CondicaoRepository;

@Service
public class CondicaoService {
	
	@Autowired
	private CondicaoRepository condicaoRepository;
	
	public Optional<Condicao> findByNomeCondicao(String condicao) {
		
		return condicaoRepository.findByNomeCondicao(condicao);
		
	}
	
	public Condicao save(Condicao condicao) {
		
		return condicaoRepository.save(condicao);
	}
}
