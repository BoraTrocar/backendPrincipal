package br.edu.fateccotia.boratroca.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import br.edu.fateccotia.boratroca.model.Livro;

@Component
@Getter
@Setter
public class UsuarioPerfilDTO {
	private String nomeCompleto;
	private String email;
	private String imagemPerfil;
	private String nickname;
	private String tipoConta;
	private List<LivroDTO> anunciosPostados;
}
