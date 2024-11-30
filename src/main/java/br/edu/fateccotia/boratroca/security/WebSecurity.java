package br.edu.fateccotia.boratroca.security;

import br.edu.fateccotia.boratroca.ouath2.OAuth2LoginSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurity {
	@Lazy
	@Autowired
	private FilterToken filter;

	@Autowired
	private OAuth2UserService oAuth2UserService;

	@Autowired
	private OAuth2LoginSucessHandler oAuth2LoginSucessHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
				.csrf(csrf -> csrf.disable()) // Desabilita CSRF
				.cors(cors -> {})            // Habilita CORS (personalizável se necessário)
				.sessionManagement(session ->
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura como Stateless
				)
				.authorizeHttpRequests(auth ->
						auth
								.requestMatchers(HttpMethod.GET, "/livro/cadastrar").authenticated() // Regras específicas
								.requestMatchers(HttpMethod.POST, "/livro/cadastrar").authenticated()
								.anyRequest().permitAll() // Permite todas as outras requisições
				)
				.oauth2Login(oauth ->
						oauth
								.loginPage("/login") // Página de login customizada
								.userInfoEndpoint(userInfo ->
										userInfo.userService(oAuth2UserService) // Serviço personalizado para extrair dados do usuário
								)
								.successHandler(oAuth2LoginSucessHandler) // Define o SuccessHandler
				)
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Filtro customizado
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
