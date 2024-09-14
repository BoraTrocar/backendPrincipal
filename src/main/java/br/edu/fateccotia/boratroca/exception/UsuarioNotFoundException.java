package br.edu.fateccotia.boratroca.exception;


public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
