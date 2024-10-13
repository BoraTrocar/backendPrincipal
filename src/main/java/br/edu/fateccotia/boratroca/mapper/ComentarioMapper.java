package br.edu.fateccotia.boratroca.mapper;

import br.edu.fateccotia.boratroca.dto.CadastrarComentarioDTO;
import br.edu.fateccotia.boratroca.dto.ComentarioDTO;
import br.edu.fateccotia.boratroca.model.Comentario;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {
    public ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setComentario(comentario.getComentario());
        comentarioDTO.setUsuario(comentario.getUsuario().getNomeUsuario());

        return  comentarioDTO;
    }

    public  Comentario toEntity (CadastrarComentarioDTO cadastrarComentarioDTO, Livro livro, Usuario usuario) {
        Comentario comentario = new Comentario();

        comentario.setComentario(cadastrarComentarioDTO.getComentario());
        comentario.setLivro(livro);
        comentario.setUsuario(usuario);

        return comentario;
    }
}
