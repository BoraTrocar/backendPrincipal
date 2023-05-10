package br.edu.fateccotia.boratroca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	@GetMapping("/cadastrar")
	public ResponseEntity<?> cadastrar() {
		return ResponseEntity.status(HttpStatus.CREATED).body("Deu certo");
	}
	
}
