package br.edu.fateccotia.boratroca.exception;

public class UsuarioUnauthorizedExecption extends RuntimeException {
    public UsuarioUnauthorizedExecption(String message) {
        super(message);
    }
    public UsuarioUnauthorizedExecption() {
      super("Você não tem permissão");
    }
}
