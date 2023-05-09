package br.edu.fateccotia.boratroca.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.edu.fateccotia.boratroca.model.Usuario;
import br.edu.fateccotia.boratroca.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	private final UsuarioRepository usuarioRepository;
	public UsuarioService(UsuarioRepository taskRepository) {

		this.usuarioRepository = taskRepository;
	}

	
	
	// Cadastra o usuario
	public Usuario save(Usuario usuario) {

		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"+email));
		return usuario;
	}



	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
