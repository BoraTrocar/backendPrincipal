package br.edu.fateccotia.boratroca.mapper;

import br.edu.fateccotia.boratroca.dto.ComentarioDTO;
import br.edu.fateccotia.boratroca.model.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {
    public ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setComentario(comentario.getComentario());
        comentarioDTO.setUsuario(comentario.getUsuario().getNomeUsuario());

        return  comentarioDTO;
    }
}
