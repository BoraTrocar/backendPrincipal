package br.edu.fateccotia.boratroca.service;

import br.edu.fateccotia.boratroca.dto.CadastrarComentarioDTO;
import br.edu.fateccotia.boratroca.dto.ComentarioDTO;
import br.edu.fateccotia.boratroca.mapper.ComentarioMapper;
import br.edu.fateccotia.boratroca.model.Comentario;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    LivroService livroService;

    @Autowired
    ComentarioMapper comentarioMapper;


    public Comentario save(CadastrarComentarioDTO cadastrarComentarioDTO, String authorization) {
        Optional<Usuario> usuario = usuarioService.findByEmail(tokenService.getSubject(authorization));
        Livro livro = livroService.findLivroById(cadastrarComentarioDTO.getIdLivro());
        Comentario comentario = comentarioMapper.toEntity(cadastrarComentarioDTO, livro, usuario.get());

        return comentarioRepository.save(comentario);
    }

    public List<ComentarioDTO> findComentariosByLivro(int id) {
        Livro livro = livroService.findLivroById(id);
        Optional<List<Comentario>> comentarios = comentarioRepository.findComentariosByLivro(livro);

        if (comentarios.isPresent()) {
            List<ComentarioDTO> comentarioDTOS = new ArrayList<ComentarioDTO>();
            for (Comentario comentario : comentarios.get()) {
                comentarioDTOS.add(comentarioMapper.toDTO(comentario));
            }
            return comentarioDTOS;
        } else {
            return null;
        }
    }
}