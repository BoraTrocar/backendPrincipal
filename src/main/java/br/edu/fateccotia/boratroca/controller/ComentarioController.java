package br.edu.fateccotia.boratroca.controller;

import br.edu.fateccotia.boratroca.model.Comentario;
import br.edu.fateccotia.boratroca.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    @Autowired
    ComentarioService comentarioService;
    @PostMapping("/cadastrar")
    public ResponseEntity<Comentario> save(@RequestBody Comentario comentario) {
        Comentario comentarioCriado = comentarioService.save(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioCriado);
    }

    @GetMapping("/livro/{id}")
    public ResponseEntity<List<Comentario>> findByLivro(@PathVariable(name = "id") String id) {
        List<Comentario> comentariosPub = comentarioService.findByLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body(comentariosPub);
    }
}