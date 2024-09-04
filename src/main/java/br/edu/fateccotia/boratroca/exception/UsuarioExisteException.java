package br.edu.fateccotia.boratroca.exception;

public class UsuarioExisteException extends RuntimeException {
    public UsuarioExisteException(String message) {
        super(message);
    }
}
