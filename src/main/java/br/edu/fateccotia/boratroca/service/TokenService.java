package br.edu.fateccotia.boratroca.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.edu.fateccotia.boratroca.model.Usuario;
@Service
public class TokenService {
	public String gerarToken(Usuario usuario) {
		return JWT.create()
			.withIssuer("Livro")
			.withSubject(usuario.getEmail())
			.withClaim("id", usuario.getIdUsuario())
			.withExpiresAt(LocalDateTime.now()
					.plusMinutes(20)
					.toInstant(ZoneOffset.of("-03:00"))
			).sign(Algorithm.HMAC256("secreta"));
	}

	public String getSubject(String token) {
		return JWT
				.require(Algorithm.HMAC256("secreta"))
				.withIssuer("Livro")
				.build().verify(token).getSubject();
	}
	
	public String getClaim(String token) {
		return JWT
				.require(Algorithm.HMAC256("secreta"))
				.withIssuer("Livro")
				.build().verify(token).getClaim("claim").asString();
	}
}
