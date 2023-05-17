package br.edu.fateccotia.boratroca.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.service.AutorService;
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
//	
//	@Autowired 
//	private CondicaoService condicaoService;
//	
//	@Autowired
//	private Ca

	
		
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Livro livro, @RequestHeader String tokenHeader) {
		
		String tokenEmail = tokenService.getSubject(tokenHeader);
		Optional<Autor> autor = autorService.findByAutor(livro.getAutor());
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		livro.setUsuario(usuario.get());
		
		if(!autor.isEmpty()) {
			livro.setAutor(autor.get());
			autor.get().setLivro(new ArrayList<Livro>());
		}
		
		Livro livroCriado = livroService.save(livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
	}
	
	
}
