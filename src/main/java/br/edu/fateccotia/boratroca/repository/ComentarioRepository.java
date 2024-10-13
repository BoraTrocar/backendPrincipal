package br.edu.fateccotia.boratroca.repository;

import br.edu.fateccotia.boratroca.model.Comentario;
import br.edu.fateccotia.boratroca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    Optional<List<Comentario>> findComentariosByLivro(Livro livro);
}
