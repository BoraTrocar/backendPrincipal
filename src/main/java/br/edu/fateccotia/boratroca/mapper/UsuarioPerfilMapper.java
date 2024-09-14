package br.edu.fateccotia.boratroca.mapper;

import br.edu.fateccotia.boratroca.dto.UsuarioPerfilDTO;
import br.edu.fateccotia.boratroca.model.Usuario;

public class UsuarioPerfilMapper {
    private Usuario usuarioModel;
    private UsuarioPerfilDTO usuarioPerfilDTO;

    public UsuarioPerfilDTO toDTO(Usuario usuario) {
        this.usuarioPerfilDTO.setEmail(usuario.getEmail());
        this.usuarioPerfilDTO.setNomeCompleto(usuario.);
        return null;
    }
}
