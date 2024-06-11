package br.edu.fateccotia.boratroca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.dto.LivroMapper;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/livro")
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
	public ResponseEntity<Livro> cadastrar(@ModelAttribute LivroDTO livroDTO, @RequestHeader String Authorization) throws IOException {

		String tokenEmail = tokenService.getSubject(Authorization);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		Optional<Autor> autor = autorService.findByNomeAutor(livroDTO.getAutor());
		Optional<Condicao> condicao = condicaoService.findByNomeCondicao(livroDTO.getCondicao());
		Optional<Categoria> categoria = categoriaService.findByNomeCategoria(livroDTO.getCategoria());

		if(usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		Livro livro = livroMapper.toEntity(livroDTO);

		livro.setUsuario(usuario.get());

		if (condicao.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			livro.setCondicao(condicao.get());
		}

		if (autor.isEmpty()) {
			Autor novoAutor = autorService.save(new Autor(livroDTO.getAutor()));
			livro.setAutor(novoAutor);
		} else {
			livro.setAutor(autor.get());
		}

		if(categoria.isEmpty()) {
			Categoria novaCategoria = categoriaService.save(new Categoria(livroDTO.getCategoria()));
			livro.setCategoria(novaCategoria);
		} else {
			livro.setCategoria(categoria.get());
		}

		Livro novoLivro = livroService.save(livro);

		return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<LivroDTO>> findAll() {

		List<Livro> livros = livroService.findAll();
		List<LivroDTO> livroDTO = new ArrayList<LivroDTO>();

        for (Livro livro : livros) {
			livroDTO.add(livroMapper.toDTO(livro));
        }

		return ResponseEntity.status(HttpStatus.OK).body(livroDTO);

	}

	@GetMapping("/buscar_livro/{id}")
	public ResponseEntity<LivroDTO> findByIdLivro(@PathVariable(name = "id") Integer id) {
		try{
			Optional<Livro> livro = livroService.findByIdLivro(id);
			if (livro.isPresent()) {
				LivroDTO livroDTO = livroMapper.toDTO(livro.get());
				return ResponseEntity.status(HttpStatus.OK).body(livroDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (TokenExpiredException expiredException) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Livro> deletarLivro(@PathVariable(name = "id") Integer id,
			@RequestHeader String Authorization) {
		String tokenEmail = tokenService.getSubject(Authorization);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		Optional<Livro> livro = livroService.findByIdLivro(id);

		if (livro.isPresent()) {
			if (usuario.get().getIdUsuario() == livro.get().getUsuario().getIdUsuario()) {
				Livro livroDeletado = livroService.delete(id);
				return ResponseEntity.status(HttpStatus.OK).body(livroDeletado);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Livro> alterarLivro(@PathVariable(name = "id") Integer id, @RequestBody Livro livro,
			@RequestHeader String Authorization) {
		
		Optional<Livro> livroFind = livroService.findByIdLivro(id);
		String tokenEmail = tokenService.getSubject(Authorization);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		Optional<Autor> autorFind = autorService.findByNomeAutor(livro.getAutor().getNomeAutor());
		Optional<Condicao> condicaoFind = condicaoService.findByNomeCondicao(livro.getCondicao().getNomeCondicao());
		Optional<Categoria> categoriaFind = categoriaService.findByNomeCategoria(livro.getCategoria().getNomeCategoria());;
		
		
		if (condicaoFind.isEmpty()) {
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
		
		
		if (usuario.get().getIdUsuario() == livroFind.get().getUsuario().getIdUsuario()) {
			if (livroFind.isPresent()) {
				if (livro.getNomeLivro() != null) {
					livroFind.get().setNomeLivro(livro.getNomeLivro());
				}

				if (livro.getIsbn() != null) {
					livroFind.get().setIsbn(livro.getIsbn());
				}

				if (livro.getDescricao() != null) {
					livroFind.get().setDescricao(livro.getDescricao());
				}

				if (livro.getCondicao() != null) {
					livroFind.get().setCondicao(livro.getCondicao());
				}

				if (livro.getCategoria() != null) {
					livroFind.get().setCategoria(livro.getCategoria());
				}

				if (livro.getAutor() != null) {
					livroFind.get().setAutor(livro.getAutor());
				}

				
				
				Livro livroAlterado = livroService.save(livroFind.get());
				return ResponseEntity.status(HttpStatus.OK).body(livroAlterado);

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	@PostMapping("/pesquisar/{parametro}")
	public ResponseEntity<List<LivroDTO>> alterarLivro(@PathVariable(name = "parametro") String parametro) {

		Optional<List<Livro>> livros = (livroService.findAllByNomeLivroOrDescricaoOrAutorNomeOrCategoriaNomeLike(parametro));

		if(livros.isPresent()) {
			List<LivroDTO> livrosDTO = new ArrayList<LivroDTO>();
			for (Livro livro : livros.get()) {
				livrosDTO.add(livroMapper.toDTO(livro));
			}
			return ResponseEntity.status(HttpStatus.OK).body(livrosDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
    }
}
