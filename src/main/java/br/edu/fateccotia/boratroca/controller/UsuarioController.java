package br.edu.fateccotia.boratroca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
