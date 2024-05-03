package br.edu.fateccotia.boratroca.dto;

import br.edu.fateccotia.boratroca.model.Livro;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class LivroMapper {
    public Livro toEntity(LivroDTO dto) throws IOException {
        return new Livro(dto.getNomeLivro(), dto.getIsbn(), dto.getDescricao(), dto.getImagemFile());
    }
    public LivroDTO toDTO(Livro entity) {
        return new LivroDTO(
                entity.getIdLivro(),
                entity.getNomeLivro(),
                entity.getIsbn(),
                entity.getDescricao(),
                entity.getImagem(),
                entity.getCondicao().getNomeCondicao(),
                entity.getCategoria().getNomeCategoria(),
                entity.getAutor().getNomeAutor(),
                entity.getUsuario().getNomeUsuario()
        );

    }

}
