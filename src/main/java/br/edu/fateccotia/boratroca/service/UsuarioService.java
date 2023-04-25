package br.edu.fateccotia.boratroca.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository taskRepository) {

		this.usuarioRepository = taskRepository;
	}

	// Cadastra o usuario
	public Usuario save(Usuario usuario) {

		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
		return usuarioRepository.findByEmailAndSenha(email, senha);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Optional<Usuario> findByNickname(String nickname) {
		return usuarioRepository.findByNickname(nickname);
	}
}
