package br.edu.fateccotia.boratroca.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.edu.fateccotia.boratroca.dto.LivroDTO;
import br.edu.fateccotia.boratroca.dto.LoginDTO;
import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import br.edu.fateccotia.boratroca.exception.InvalidTokenException;
import br.edu.fateccotia.boratroca.exception.UsuarioExisteException;
import br.edu.fateccotia.boratroca.exception.UsuarioNotFoundException;
import br.edu.fateccotia.boratroca.infra.DateConversor;
import br.edu.fateccotia.boratroca.mapper.UsuarioMapper;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private UsuarioMapper usuarioMapper;

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

    public UsuarioDTO mostrarPerfil(String Authorization) {
        Usuario usuario = buscarUsuarioPorToken(Authorization);
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

        List<LivroDTO> livros = livroService.findAllByUsuario(usuario);
        usuarioDTO.setAnunciosPostados(livros != null ? livros : Collections.emptyList());

        return usuarioDTO;
    }

    public String logar(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var usuario = (Usuario) authenticate.getPrincipal();
        return String.format("{\"token\":\"%s\"}", tokenService.gerarToken(usuario));

    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Atualiza a notificação do usuário
     *
     * @param Authorization
     * @param notificacao
     *
     * @return Usuario
     */
    public Usuario atualizarNotificacao(String Authorization, boolean notificacao) {
        Usuario usuario = buscarUsuarioPorToken(Authorization);
        usuario.setNotificacao(notificacao);

        return usuarioRepository.save(usuario);
    }

    /**
     * Atualiza o raio de busca do usuário
     *
     * @param Authorization
     * @param raio
     *
     * @return Usuario
     */
    public Usuario atualizarRaio(String Authorization, Double raio) {
        Usuario usuario = buscarUsuarioPorToken(Authorization);
        usuario.setRaio(raio);

        return usuarioRepository.save(usuario);
    }

    /**
     * Busca o usuário pelo token
     *
     * @param Authorization
     * @return Usuario
     */
    private Usuario buscarUsuarioPorToken(String Authorization) {
        try {
            String email = tokenService.getSubject(Authorization);
            return usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado."));
        } catch (JWTDecodeException e) {
            throw new InvalidTokenException("Token inválido.");
        }
    }
}