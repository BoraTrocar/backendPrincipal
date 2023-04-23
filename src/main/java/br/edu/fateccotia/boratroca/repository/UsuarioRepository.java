package br.edu.fateccotia.boratroca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateccotia.boratroca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
