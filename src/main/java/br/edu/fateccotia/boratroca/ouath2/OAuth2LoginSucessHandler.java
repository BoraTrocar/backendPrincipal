package br.edu.fateccotia.boratroca.ouath2;

import br.edu.fateccotia.boratroca.controller.UsuarioController;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.repository.UsuarioRepository;
import br.edu.fateccotia.boratroca.service.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Lazy
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Lazy
    @Autowired
    private TokenService tokenService;

    @Autowired
    private Oauth2Controller oauth2Controller;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Obtém os dados do usuário autenticado via OAuth2
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oauthToken.getPrincipal();

        Optional<Usuario> usuarioFind = usuarioRepository.findByEmail(oAuth2User.getAttribute("email"));

        if (usuarioFind.isPresent()) {
            String token =  tokenService.gerarToken(usuarioFind.get());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"token\": \"" + token + "\"}");
            response.getWriter().flush();

        } else {
            Usuario usuario = new Usuario();
            usuario.setNomeUsuario(oAuth2User.getAttribute("name"));
            usuario.setEmail(oAuth2User.getAttribute("email"));
            usuario.setImagemPerfil(oAuth2User.getAttribute("avatar_url"));
            usuario.setNickname(oAuth2User.getAttribute("login"));
            usuario.setSenha("abacaxi");

            tokenService.gerarToken(usuarioRepository.save(usuario));

            Optional<Usuario> userCreated = usuarioRepository.findByEmail(oAuth2User.getAttribute("email"));

            String token = tokenService.gerarToken(userCreated.get());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"token\": \"" + token + "\"}");
            response.getWriter().flush();

        }
    }
}
