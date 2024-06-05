package br.edu.fateccotia.boratroca.controller;

import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import br.edu.fateccotia.boratroca.dto.UsuarioPerfilDTO;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.service.LivroService;
import br.edu.fateccotia.boratroca.service.TokenService;
import br.edu.fateccotia.boratroca.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UsuarioPerfilDTO usuarioPerfilDTO;

    @Mock
    private LivroService livroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUsuarioSuccess() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");

        when(usuarioService.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        when(encoder.encode(usuario.getSenha())).thenReturn("encodedPassword");
        when(usuarioService.save(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.create(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testCreateUsuarioEmailExists() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
    
        when(usuarioService.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
    
        ResponseEntity<Usuario> response = usuarioController.create(usuario);
    
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testLogarUsuarioSuccess() {
        String email = "test@example.com";
        String senha = "password";
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(email);
        usuarioDTO.setSenha(senha);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, senha);
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(authRequest)).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(usuario);
        when(tokenService.gerarToken(usuario)).thenReturn("generatedToken");

        String response = usuarioController.logar(usuarioDTO);

        assertEquals("{\"token\":\"generatedToken\"}", response);
    }

    @Test
    void testMostrarPerfilSuccess() {
        String token = "token";
        String email = "user@example.com";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNomeUsuario("User");
        usuario.setNickname("user123");
        usuario.setPremium(false);

        when(tokenService.getSubject(token)).thenReturn(email);
        when(usuarioService.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(livroService.findAllByUsuario(usuario)).thenReturn(null);

        ResponseEntity<UsuarioPerfilDTO> response = usuarioController.mostrarPerfil(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testMostrarPerfilForbidden() {
        String token = "token";
        String email = "user@example.com";
    
        when(tokenService.getSubject(token)).thenReturn(email);
        when(usuarioService.findByEmail(email)).thenReturn(Optional.empty());
    
        ResponseEntity<UsuarioPerfilDTO> response = usuarioController.mostrarPerfil(token);
    
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertNull(response.getBody());
    }
}
