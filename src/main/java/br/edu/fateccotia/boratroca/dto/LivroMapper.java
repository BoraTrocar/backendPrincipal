package br.edu.fateccotia.boratroca.dto;

import br.edu.fateccotia.boratroca.model.Livro;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class LivroMapper {
    public Livro toEntity(LivroDTO dto) throws IOException {
        Livro livro = new Livro();
        livro.setNomeLivro(dto.getNomeLivro());
        livro.setIsbn(dto.getIsbn());
        livro.setDescricao(dto.getDescricao());
        if (dto.getImagemFile() != null) {
            livro.setImagem(dto.getImagemFile().getBytes());
        }
        return livro;
    }
    public LivroDTO toDTO(Livro entity) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setIdLivro(entity.getIdLivro());
        livroDTO.setNomeLivro(entity.getNomeLivro());
        livroDTO.setIsbn(entity.getIsbn());
        livroDTO.setDescricao(entity.getDescricao());
        livroDTO.setImagemBlob(entity.getImagem());
        livroDTO.setCondicao(entity.getCondicao().getNomeCondicao());
        livroDTO.setCategoria(entity.getCategoria().getNomeCategoria());
        livroDTO.setAutor(entity.getAutor().getNomeAutor());
        livroDTO.setUsuario(entity.getUsuario().getNomeUsuario());
        return livroDTO;
    }
}
