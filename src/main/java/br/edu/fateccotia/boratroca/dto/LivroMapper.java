package br.edu.fateccotia.boratroca.dto;

import br.edu.fateccotia.boratroca.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LivroMapper {
    private ModelMapper mapper;
    public Livro toEntity(LivroDTO dto) throws IOException {
        Livro livro = new Livro(dto.getNomeLivro(), dto.getIsbn(), dto.getDescricao(), dto.getImagem());
        return livro;
    }
    public LivroDTO toDTO(Livro entity) {
        return mapper.map(entity, LivroDTO.class);
    }

}
