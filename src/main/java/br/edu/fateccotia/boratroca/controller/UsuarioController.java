package br.edu.fateccotia.boratroca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> create(@RequestBody Usuario user) {

		Usuario userCreated = usuarioService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<Optional<Usuario>> logar(@RequestBody UsuarioDTO usuarioDTO) {
		
		 // Extrair o email e a senha do objeto DTO
	    String email = usuarioDTO.getEmail();
	    String senha = usuarioDTO.getSenha();

	    // Buscar o usuário no banco de dados usando o email e a senha
	    Optional<Usuario> usuario = usuarioService.findByEmailAndSenha(email, senha);

	    if (usuario != null) {
	    	
	        // Se o usuário for encontrado, retornar uma resposta de sucesso
	        return ResponseEntity.ok(usuario);
	        
	    } else {
	    	
	        // Se o usuário não for encontrado, retornar uma resposta de erro
	        return ResponseEntity.badRequest().body(usuario);
	    }
	}
}
