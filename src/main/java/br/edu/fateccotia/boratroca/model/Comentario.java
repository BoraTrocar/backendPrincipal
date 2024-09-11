package br.edu.fateccotia.boratroca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private int idComentario;

    @Column(unique=false)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

    public Comentario( int idComentario, String comentario ) {
        this.idComentario = idComentario;
        this.comentario = comentario;
    }

    public Comentario(int idComentario, String comentario, Livro livro, Usuario usuario ) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.usuario = usuario;
        this.livro = livro;
    }

    public Comentario() {

    }

}