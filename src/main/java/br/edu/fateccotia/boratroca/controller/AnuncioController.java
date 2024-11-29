package br.edu.fateccotia.boratroca.controller;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.service.AnuncioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Anuncios", description = "")
@RestController
@RequestMapping("/anuncio")
public class AnuncioController {
    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listAnuncios(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(anuncioService.listLivros(pageable));
    }
}
