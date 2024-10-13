package br.edu.fateccotia.boratroca.service;

import br.edu.fateccotia.boratroca.model.Comentario;
import br.edu.fateccotia.boratroca.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> findByLivro(String id) {
        return comentarioRepository.findByLivro(id);
    }
}