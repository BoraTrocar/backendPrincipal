package br.edu.fateccotia.boratroca.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import br.edu.fateccotia.boratroca.exception.InvalidTokenException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import br.edu.fateccotia.boratroca.model.Usuario;

@Service
public class TokenService {

    private static final String ISSUER = "Livro";
    private static final String SECRET = "secreta";

    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getIdUsuario())
                .withExpiresAt(LocalDateTime.now()
                        .plusDays(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(SECRET));
    }

    public String getSubject(String token) {

        return JWT
                .require(Algorithm.HMAC256(SECRET))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();

    }

    public String getClaim(String token) {
        try {
            return JWT
                    .require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER)
                    .build().verify(token).getClaim("claim").asString();
        } catch (JWTVerificationException ex) {
            throw new InvalidTokenException("Token inválido");
        }
    }
}
