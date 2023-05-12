package br.edu.fateccotia.boratroca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;

import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;
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
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Livro livro, @RequestHeader String tokenHeader) {
		
		//Livro livroCriado = livroService.save(livro);
		String tokenEmail = tokenService.getSubject(tokenHeader);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
}
