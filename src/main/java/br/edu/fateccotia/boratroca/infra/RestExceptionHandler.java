package br.edu.fateccotia.boratroca.infra;

import br.edu.fateccotia.boratroca.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundExecption.class)
    private ResponseEntity<String> notFoundHandler (NotFoundExecption execption) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(execption.getMessage());
    }

    @ExceptionHandler(UsuarioExisteException.class)
    private ResponseEntity<String> usuarioExisteHandler (UsuarioExisteException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<String> invalidTokenHandler (InvalidTokenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler(LivroMapperException.class)
    private ResponseEntity<String> livroMapperHandler (LivroMapperException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }


    @ExceptionHandler(UsuarioUnauthorizedExecption.class)
    private ResponseEntity<String> UsuarioUnauthorizedHandler (UsuarioUnauthorizedExecption exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(NotBlankException.class)
    private ResponseEntity<String> NotBlankHandler (NotBlankException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
