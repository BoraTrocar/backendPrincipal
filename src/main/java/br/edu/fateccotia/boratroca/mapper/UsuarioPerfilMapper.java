package br.edu.fateccotia.boratroca.mapper;

import br.edu.fateccotia.boratroca.dto.UsuarioPerfilDTO;
import br.edu.fateccotia.boratroca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPerfilMapper {
    public UsuarioPerfilDTO toDTO(Usuario usuario) {
       UsuarioPerfilDTO usuarioPerfilDTO = new UsuarioPerfilDTO();

       usuarioPerfilDTO.setNomeCompleto(usuario.getNomeUsuario());
       usuarioPerfilDTO.setEmail(usuario.getEmail());
       usuarioPerfilDTO.setImagemPerfil(usuario.getImagemPerfil());
       usuarioPerfilDTO.setNickname(usuario.getNickname());
       usuarioPerfilDTO.setTipoConta(null);
       usuarioPerfilDTO.setAnunciosPostados(null);
       return usuarioPerfilDTO;
    }
}
