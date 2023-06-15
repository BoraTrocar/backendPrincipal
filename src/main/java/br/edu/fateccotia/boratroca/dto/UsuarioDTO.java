package br.edu.fateccotia.boratroca.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Component
public class UsuarioDTO {
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
