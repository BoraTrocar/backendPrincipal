package br.edu.fateccotia.boratroca.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import br.edu.fateccotia.boratroca.repository.UsuarioRepository;
import br.edu.fateccotia.boratroca.service.TokenService;
import br.edu.fateccotia.boratroca.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token;
		
		var authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader != null) {
			token = authorizationHeader.replace("Bearer ", "");
			var subject = this.tokenService.getSubject(token);
			
			var usuario = this.usuarioService.loadUserByUsername(subject);
			
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
