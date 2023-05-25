package br.edu.fateccotia.boratroca.dto;

import org.springframework.stereotype.Component;

@Component
public class UsuarioPerfilDTO {
	private String nomeCompleto;
	private String email;
	private String imagem; 
	private String nickname;
	private String tipoConta;
	private int anunciosPostados;
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public int getAnunciosPostados() {
		return anunciosPostados;
	}
	public void setAnunciosPostados(int anunciosPostados) {
		this.anunciosPostados = anunciosPostados;
	}
	
	
}
