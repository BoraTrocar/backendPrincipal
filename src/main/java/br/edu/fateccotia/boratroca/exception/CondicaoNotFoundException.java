package br.edu.fateccotia.boratroca.exception;

public class CondicaoNotFoundException extends RuntimeException {
    public CondicaoNotFoundException(String message) {
        super(message);
    }
    public CondicaoNotFoundException () {
        super("Condição invalida");
    }
}
