package br.edu.fateccotia.boratroca.dto;

import lombok.Data;

@Data
public class CadastrarComentarioDTO {
    private String comentario;
    private int idLivro;
}
