package br.edu.fateccotia.boratroca.service;

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
}
