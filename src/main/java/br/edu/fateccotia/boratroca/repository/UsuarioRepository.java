package br.edu.fateccotia.boratroca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateccotia.boratroca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
	
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findByNickname(String nickname);
}
