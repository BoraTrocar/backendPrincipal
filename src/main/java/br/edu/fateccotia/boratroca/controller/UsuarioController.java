package br.edu.fateccotia.boratroca.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.exception.InvalidTokenException;
import br.edu.fateccotia.boratroca.exception.UsuarioExisteException;
import br.edu.fateccotia.boratroca.exception.UsuarioNotFoundException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
@RequestMapping("/api/usuario")    //Define a URL para acessar os metodos da classe
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioPerfilDTO usuarioPerfilDTO;

    @Autowired
    private LivroService livroService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
        Usuario userCreated = usuarioService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping("/logar")
    public ResponseEntity<String> logar(@RequestBody UsuarioDTO usuarioDTO) {
        String token = usuarioService.logar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> mostrarPerfil(@RequestHeader String Authorization) {
        UsuarioPerfilDTO usuarioPerfilDTO = usuarioService.mostrarPerfil(Authorization);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioPerfilDTO);
    }
}
