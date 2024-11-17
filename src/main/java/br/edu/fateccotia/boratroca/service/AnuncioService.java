package br.edu.fateccotia.boratroca.service;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.mapper.LivroMapper;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private LivroMapper livroMapper;


    public List<LivroDTO> listLivros(Pageable pageable) {
        List<Livro> livros = anuncioRepository.findAll(pageable).getContent();
        List<LivroDTO> livrosDTO = new ArrayList<>();

        for (Livro livro : livros) {
            livrosDTO.add(livroMapper.toDTO(livro));
        }

        return livrosDTO;
    }
}
