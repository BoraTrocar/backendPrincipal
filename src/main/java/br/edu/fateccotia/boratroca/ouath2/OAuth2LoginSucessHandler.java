package br.edu.fateccotia.boratroca.ouath2;

import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.repository.UsuarioRepository;
import br.edu.fateccotia.boratroca.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Obtém os dados do usuário autenticado via OAuth2
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oauthToken.getPrincipal();

        // Extrai atributos do usuário
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Busca ou cadastra o usuário na base de dados
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseGet(() -> {
                    Usuario newUser = new Usuario();
                    newUser.setEmail(email);
                    newUser.setNomeUsuario(name);
                    // Senha pode ser null ou um valor padrão
                    newUser.setSenha(null);
                    return usuarioRepository.save(newUser);
                });

        // Cria o objeto de autenticação sem senha
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // Registra o usuário no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // Redireciona para a página desejada
        response.sendRedirect("/livro/all"); // Página inicial ou outro destino
    }
}
