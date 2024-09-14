package br.edu.fateccotia.boratroca.exception;


public class UsuarioNotFoundException extends NotFoundExecption{
    public UsuarioNotFoundException(String message) {super(message);}
    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
