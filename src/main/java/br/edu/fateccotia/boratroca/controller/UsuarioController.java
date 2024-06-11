package br.edu.fateccotia.boratroca.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import br.edu.fateccotia.boratroca.dto.UsuarioPerfilDTO;
import br.edu.fateccotia.boratroca.model.Livro;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.service.LivroService;
import br.edu.fateccotia.boratroca.service.TokenService;
import br.edu.fateccotia.boratroca.service.UsuarioService;


@RestController //Anota a classe como um Controlador de requisições Rest
@RequestMapping("/api/usuario")	//Define a URL para acessar os metodos da classe
public class UsuarioController {

	@Autowired 
	private UsuarioService usuarioService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService; 
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioPerfilDTO usuarioPerfilDTO;
	
	@Autowired
	private LivroService livroService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> create(@RequestBody Usuario user) {
		Optional<Usuario> emailExiste = usuarioService.findByEmail(user.getEmail());		
		
		if(emailExiste.isEmpty()) {
			user.setSenha(encoder.encode(user.getSenha()));
			Usuario userCreated = usuarioService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@PostMapping("/logar")
	public String logar(@RequestBody UsuarioDTO usuarioDTO) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha());
		
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		var usuario = (Usuario)authenticate.getPrincipal();
		
		return String.format("{\"token\":\"%s\"}", tokenService.gerarToken(usuario));
	}
	
	@GetMapping("/perfil")
	public ResponseEntity<UsuarioPerfilDTO> mostrarPerfil(@RequestHeader String Authorization) {
		String tokenEmail = tokenService.getSubject(Authorization);
		Optional<Usuario> usuario = usuarioService.findByEmail(tokenEmail);
		
		if(usuario.isPresent()) {
			List<Livro> livros = livroService.findAllByUsuario(usuario.get());
			if (livros != null) {
				for (Livro livro : livros) {
					livro.getUsuario().setSenha(null);
				}
			}
			usuarioPerfilDTO.setAnunciosPostados(livros != null ? livros : Collections.emptyList());
			usuarioPerfilDTO.setNomeCompleto(usuario.get().getNomeUsuario());
			usuarioPerfilDTO.setNickname(usuario.get().getNickname());
			usuarioPerfilDTO.setEmail(usuario.get().getEmail());
			usuarioPerfilDTO.setTipoConta(usuario.get().isPremium() ? "Premium" : "Comum");
			return ResponseEntity.status(HttpStatus.OK).body(usuarioPerfilDTO);
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}	
}
