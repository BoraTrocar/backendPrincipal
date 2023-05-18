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
import br.edu.fateccotia.boratroca.model.Autor;
import br.edu.fateccotia.boratroca.model.Categoria;
import br.edu.fateccotia.boratroca.model.Condicao;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;
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

		
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Livro livro, @RequestHeader String tokenHeader) {
		
		String tokenEmail = tokenService.getSubject(tokenHeader);
		
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		Optional<Autor> autorFind = autorService.findByAutor(livro.getAutor().toString());
		Optional<Condicao> condicaoFind = condicaoService.findByCondicao(livro.getCondicao().toString());
		Optional<Categoria> categoriaFind = categoriaService.findByCategoria(livro.getCategoria().toString());
		
		livro.setUsuario(usuario.get());
		
		if(autorFind.isEmpty()) {
			Autor autor = autorService.save(livro.getAutor());
			livro.setAutor(autor);
		}
		else {
			livro.setAutor(autorFind.get());
		}
		

		livro.setCondicao(condicaoFind.get());
		
		
		if(categoriaFind.isEmpty()) {
			Categoria categoria = categoriaService.save(livro.getCategoria());
			livro.setCategoria(categoria);
		}
		else {
			livro.setCategoria(categoriaFind.get());
		}
		
		
		Livro livroCriado = livroService.save(livro);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
	}
	
	
}
