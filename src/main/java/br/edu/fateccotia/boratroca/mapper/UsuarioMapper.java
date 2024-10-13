package br.edu.fateccotia.boratroca.mapper;

import br.edu.fateccotia.boratroca.dto.UsuarioDTO;
import br.edu.fateccotia.boratroca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
       UsuarioDTO usuarioDTO = new UsuarioDTO();

       usuarioDTO.setNomeCompleto(usuario.getNomeUsuario());
       usuarioDTO.setEmail(usuario.getEmail());
       usuarioDTO.setImagemPerfil(usuario.getImagemPerfil());
       usuarioDTO.setNickname(usuario.getNickname());
       usuarioDTO.setTipoConta(null);
       usuarioDTO.setAnunciosPostados(null);
       return usuarioDTO;
    }
}
