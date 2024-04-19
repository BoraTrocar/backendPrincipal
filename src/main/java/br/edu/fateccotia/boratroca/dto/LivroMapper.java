package br.edu.fateccotia.boratroca.dto;

import br.edu.fateccotia.boratroca.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {
    private ModelMapper mapper;
    public Livro toEntity(LivroDTO dto) {
        return mapper.map(dto, Livro.class);
    }
    public LivroDTO toDTO(Livro entity) {
        return mapper.map(entity, LivroDTO.class);
    }

}
