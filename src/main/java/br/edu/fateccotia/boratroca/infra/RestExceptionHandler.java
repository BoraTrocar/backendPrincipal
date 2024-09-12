package br.edu.fateccotia.boratroca.infra;

import br.edu.fateccotia.boratroca.dto.LivroMapper;
import br.edu.fateccotia.boratroca.exception.*;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(UsuarioExisteException.class)
    private ResponseEntity<String> usuarioExisteHandler (UsuarioExisteException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<String> invalidTokenHandler (InvalidTokenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler(CondicaoNotFoundException.class)
    private ResponseEntity<String> condicaoNotFoundHandler (CondicaoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(LivroMapperException.class)
    private ResponseEntity<String> livroMapperHandler (LivroMapperException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(LivroNotFoundException.class)
    private ResponseEntity<String> livroNotFoundHandler (LivroNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(UsuarioUnauthorizedExecption.class)
    private ResponseEntity<String> UsuarioUnauthorizedHandler (UsuarioUnauthorizedExecption exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
