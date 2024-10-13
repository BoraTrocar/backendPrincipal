package br.edu.fateccotia.boratroca.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import br.edu.fateccotia.boratroca.dto.UsuarioPerfilDTO;
import br.edu.fateccotia.boratroca.exception.InvalidTokenException;
import br.edu.fateccotia.boratroca.exception.UsuarioExisteException;
import br.edu.fateccotia.boratroca.exception.UsuarioNotFoundException;
import br.edu.fateccotia.boratroca.infra.DateConversor;
import br.edu.fateccotia.boratroca.mapper.UsuarioPerfilMapper;
import br.edu.fateccotia.boratroca.model.Livro;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Lazy
    @Autowired
    private LivroService livroService;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioPerfilMapper usuarioPerfilMapper;

    @Autowired
    private DateConversor dateConversor;

    // Cadastra o usuario
    public Usuario save(Usuario usuario) {
        Optional<Usuario> emailExiste = usuarioRepository.findByEmail(usuario.getEmail());
        if (emailExiste.isPresent()) {
            throw new UsuarioExisteException("Email já cadastro");
        } else {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            usuario.setDataNascimento(dateConversor.converterParaFormatoAmericano(usuario.getDataNascimento().toString()));
            return usuarioRepository.save(usuario);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado" + email));
    }

    public Optional<Usuario> findByEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            return usuario;
        } else {
            throw new UsuarioNotFoundException();
        }

    }

    public UsuarioPerfilDTO mostrarPerfil(String Authorization) {
        try {
            String tokenEmail = tokenService.getSubject(Authorization);
            Optional<Usuario> usuario = usuarioRepository.findByEmail(tokenEmail);

            if (usuario.isPresent()) {
                UsuarioPerfilDTO usuarioPerfilDTO =  usuarioPerfilMapper.toDTO(usuario.get());
                List<LivroDTO> livros = livroService.findAllByUsuario(usuario.get());
                usuarioPerfilDTO.setAnunciosPostados(livros != null ? livros : Collections.emptyList());
                return usuarioPerfilDTO;
            } else {
                throw new UsuarioNotFoundException();
            }

        } catch (JWTDecodeException e) {
            //Não funciona como deveria, precisa de correção :-)
            throw new InvalidTokenException("Token Invalido");
        }

    }

    public String logar(UsuarioDTO usuarioDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha());

            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            var usuario = (Usuario) authenticate.getPrincipal();
            return String.format("{\"token\":\"%s\"}", tokenService.gerarToken(usuario));

    }
}
