package com.sulimann.springsecurityexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sulimann.springsecurityexample.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
