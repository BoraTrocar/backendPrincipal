package br.edu.fateccotia.boratroca.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UsuarioDTO {
	private String nomeCompleto;
	private String email;
	private String imagemPerfil;
	private String nickname;
	private String tipoConta;
	private Boolean notificacao;
	private Double raio;
	private List<LivroDTO> anunciosPostados;
}
