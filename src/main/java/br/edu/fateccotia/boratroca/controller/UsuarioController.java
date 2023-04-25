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


@RestController //Anota a classe como um Controlador de requisições Rest
@RequestMapping("/usuario")	//Define a URL para acessar os metodos da classe
public class UsuarioController {

	@Autowired //Faz a injeção de dependencia da classe UsuarioService
	private UsuarioService usuarioService;

	@PostMapping("/cadastrar") //Define o mapeamento de um metodo post na URL /cadastrar
	public ResponseEntity<?> create(@RequestBody Usuario user) {

		Optional<Usuario> emailExiste = usuarioService.findByEmail(user.getEmail());
		Optional<Usuario> nicknameExiste = usuarioService.findByNickname(user.getEmail());
		
		if(emailExiste.isEmpty()) {
			
			Usuario userCreated = usuarioService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
			
		} 
		
		else if(nicknameExiste.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome de usuario já cadastrado");
			
		}
		
		
		else {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O email já está cadastrado");
		}
		
		
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
	        return ResponseEntity.status(HttpStatus.OK).body(usuario);
	        
	    } else {
	    	
	        // Se o usuário não for encontrado, retornar uma resposta de erro
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuario);
	    }
	}
}
