package br.edu.fateccotia.boratroca.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class UsuarioDTO {
	private String email;
	
	private String senha;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
