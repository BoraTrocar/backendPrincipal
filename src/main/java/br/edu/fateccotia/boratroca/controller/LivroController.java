package br.edu.fateccotia.boratroca.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro, @RequestHeader String Authorization) {

		String tokenEmail = tokenService.getSubject(Authorization);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		Optional<Autor> autorFind = autorService.findByNomeAutor(livro.getAutor().getNomeAutor());
		Optional<Condicao> condicaoFind = condicaoService.findByNomeCondicao(livro.getCondicao().getNomeCondicao());
		Optional<Categoria> categoriaFind = categoriaService
				.findByNomeCategoria(livro.getCategoria().getNomeCategoria());
		
		
		if(usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		livro.setUsuario(usuario.get());
		
		if(condicaoFind.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		livro.setCondicao(condicaoFind.get());

		if (autorFind.isEmpty()) {
			Autor autor = autorService.save(livro.getAutor());
			livro.setAutor(autor);
		} else {
			livro.setAutor(autorFind.get());
		}

		if (categoriaFind.isEmpty()) {
			Categoria categoria = categoriaService.save(livro.getCategoria());
			livro.setCategoria(categoria);
		} else {
			livro.setCategoria(categoriaFind.get());
		}

		Livro livroCriado = livroService.save(livro);

		return ResponseEntity.status(HttpStatus.OK).body(livroCriado);
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Livro>> findAll() {

		List<Livro> livros = livroService.findAll();
		
		for (int i = 0; i < livros.size(); i++) {
			
			livros.get(i).getUsuario().setSenha(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(livros);

	}

	@GetMapping("/buscar_livro/{id}")
	public ResponseEntity<Livro> findByIdLivro(@PathVariable(name = "id") Integer id) {
		Optional<Livro> livro = livroService.findByIdLivro(id);

		if (!livro.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(livro.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Livro> deletarLivro(@PathVariable(name = "id") Integer id, @RequestHeader String Authorization) {
		String tokenEmail = tokenService.getSubject(Authorization);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		Optional<Livro> livro = livroService.findByIdLivro(id);
		
		
		if (!livro.isEmpty()) {
			if(usuario.get().getIdUsuario() == livro.get().getUsuario().getIdUsuario()) {
				Livro livroDeletado = livroService.delete(id);
				return ResponseEntity.status(HttpStatus.OK).body(livroDeletado);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/alterar")
	public ResponseEntity<Livro> alterarLivro(@RequestBody Livro livro, @RequestHeader String Authorization) {
		
		return null;
	}
}
