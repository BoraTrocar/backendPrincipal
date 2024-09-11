package br.edu.fateccotia.boratroca.repository;

import br.edu.fateccotia.boratroca.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String> {
    List<Comentario> findByLivro(String Livro);
}
