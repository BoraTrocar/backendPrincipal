package br.edu.fateccotia.boratroca.controller;

import br.edu.fateccotia.boratroca.dto.CadastrarComentarioDTO;
import br.edu.fateccotia.boratroca.dto.ComentarioDTO;
import br.edu.fateccotia.boratroca.model.Comentario;
import br.edu.fateccotia.boratroca.service.ComentarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comentarios", description = "")
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Comentario> save(@RequestBody CadastrarComentarioDTO cadastrarComentarioDto, @RequestHeader String authorization) {
        Comentario comentario = comentarioService.save(cadastrarComentarioDto, authorization);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ComentarioDTO>> findComentariosByLivro(@PathVariable(name = "id") int id) {
        List<ComentarioDTO> comentariosPub = comentarioService.findComentariosByLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body(comentariosPub);
    }
}