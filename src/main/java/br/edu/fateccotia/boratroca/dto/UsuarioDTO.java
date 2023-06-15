package br.edu.fateccotia.boratroca.dto;

import org.springframework.stereotype.Component;


@Component
public class UsuarioDTO {
	private String email;
	
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
