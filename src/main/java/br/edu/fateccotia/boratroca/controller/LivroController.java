package br.edu.fateccotia.boratroca.controller;

import java.io.IOException;
import java.util.List;
import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.dto.LivroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.service.AutorService;
import br.edu.fateccotia.boratroca.service.CategoriaService;
import br.edu.fateccotia.boratroca.service.CondicaoService;
import br.edu.fateccotia.boratroca.service.LivroService;
import br.edu.fateccotia.boratroca.service.TokenService;
import br.edu.fateccotia.boratroca.service.UsuarioService;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private CondicaoService condicaoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LivroMapper livroMapper;

    @PostMapping("/cadastrar")
    @ResponseBody
    public ResponseEntity<Livro> cadastrar(@ModelAttribute LivroDTO livroDTO, @RequestHeader String authorization) throws IOException {
        Livro livro = livroService.cadastrar(livroDTO, authorization);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<LivroDTO>> findAll() {
        List<LivroDTO> livroDTO = livroService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(livroDTO);
    }

    @GetMapping("/buscar_livro/{id}")
    public ResponseEntity<LivroDTO> findByIdLivro(@PathVariable(name = "id") Integer id) {
        LivroDTO livro = livroService.findByIdLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable(name = "id") Integer id, @RequestHeader String authorization) {
        livroService.delete(id, authorization);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso");
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarLivro(@PathVariable(name = "id") Integer id, @RequestBody Livro livro, @RequestHeader String authorization) {
        livroService.alterarLivro(id, livro,authorization);
        return ResponseEntity.status(HttpStatus.OK).body("Livro criado com sucesso");
    }

    @PostMapping("/pesquisar/{parametro}")
    public ResponseEntity<List<LivroDTO>> pesquisarLivro(@PathVariable(name = "parametro") String parametro) {
        List<LivroDTO> livros = livroService.pesquisarLivro(parametro);
        return ResponseEntity.status(HttpStatus.OK).body(livros);
    }
}
