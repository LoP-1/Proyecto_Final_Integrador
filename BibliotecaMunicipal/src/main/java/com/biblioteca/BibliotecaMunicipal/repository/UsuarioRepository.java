package com.biblioteca.BibliotecaMunicipal.repository;

import com.biblioteca.BibliotecaMunicipal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
}
