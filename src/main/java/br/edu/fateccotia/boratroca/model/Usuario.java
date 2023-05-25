package br.edu.fateccotia.boratroca.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuario")

public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8017284848458232219L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true)
	private int idUsuario;
	private String nomeUsuario;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String nickname;
	private String senha;
	private Date dataNascimento;
	private boolean premium = false;
		
	public Usuario(String nomeUsuario, String email, String nickname, String senha, Date dataNascimento,
		   boolean premium) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.nickname = nickname;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.premium = premium;
		
	}

	public Usuario() {
		
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	//User details
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@JsonIgnore
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}



