package br.edu.fateccotia.boratroca.exception;

public class NotBlankException extends RuntimeException {
    public NotBlankException(String message) {super(message);}
    public NotBlankException() {super("O campo não pode ser vazio");}
}
