package br.edu.fateccotia.boratroca.infra;

import br.edu.fateccotia.boratroca.exception.InvalidTokenException;
import br.edu.fateccotia.boratroca.exception.UsuarioExisteException;
import br.edu.fateccotia.boratroca.exception.UsuarioNotFoundException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsuarioNotFoundException.class)
    private ResponseEntity<String> usuarioNotFoundHandler (UsuarioNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    @ExceptionHandler(UsuarioExisteException.class)
    private ResponseEntity<String> usuarioExisteHandler (UsuarioExisteException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já está cadastrado");
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<String> invalidTokenHandler (InvalidTokenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token Invalido");
    }
}
