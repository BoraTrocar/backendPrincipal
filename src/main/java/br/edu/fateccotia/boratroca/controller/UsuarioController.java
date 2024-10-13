package br.edu.fateccotia.boratroca.controller;

import br.edu.fateccotia.boratroca.dto.LoginDTO;
import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.service.LivroService;
import br.edu.fateccotia.boratroca.service.UsuarioService;


@RestController //Anota a classe como um Controlador de requisições Rest
@RequestMapping("/usuario")    //Define a URL para acessar os metodos da classe
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioDTO usuarioDTO;

    @Autowired
    private LivroService livroService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
        Usuario userCreated = usuarioService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping("/logar")
    public ResponseEntity<String> logar(@RequestBody LoginDTO loginDTO) {
        String token = usuarioService.logar(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> mostrarPerfil(@RequestHeader String Authorization) {
        UsuarioDTO usuarioDTO = usuarioService.mostrarPerfil(Authorization);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }
}
