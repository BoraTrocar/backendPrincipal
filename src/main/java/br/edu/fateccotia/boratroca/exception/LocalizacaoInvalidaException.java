package br.edu.fateccotia.boratroca.exception;

public class LocalizacaoInvalidaException extends NotFoundExecption {
    public LocalizacaoInvalidaException(String message) {
        super(message);
    }

    public LocalizacaoInvalidaException() {
    super("Localização não informada");
  }
}
