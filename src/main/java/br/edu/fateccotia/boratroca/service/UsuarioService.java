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
	public UsuarioService(UsuarioRepository usuarioRepository) {

		this.usuarioRepository = usuarioRepository;
	}

	
	
	// Cadastra o usuario
	public Usuario save(Usuario usuario) {

		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return
				usuarioRepository.findByEmail(email)
						.orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"+email));
	}



	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
