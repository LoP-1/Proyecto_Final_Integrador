package com.biblioteca.BibliotecaMunicipal.sevice;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.UsuarioDTO;
import com.biblioteca.BibliotecaMunicipal.model.Rol;
import com.biblioteca.BibliotecaMunicipal.model.Usuario;
import com.biblioteca.BibliotecaMunicipal.repository.RolRepository;
import com.biblioteca.BibliotecaMunicipal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Ahora el registro trabaja con el DTO
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        Rol rol = rolRepository.findByNombre(usuarioDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setDni(usuarioDTO.getDni());
        usuario.setContraseña(usuarioDTO.getContraseña());
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> actualizarUsuario(Integer id, UsuarioDTO usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setDni(usuarioActualizado.getDni());
            Rol rol = rolRepository.findByNombre(usuarioActualizado.getRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
            return usuarioRepository.save(usuario);
        });
    }

    public boolean eliminarUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Servicio de login
    public Optional<Usuario> login(String usuario, String contraseña) {
        // Buscar por correo (ajusta si usas otro campo)
        return usuarioRepository.findByCorreoAndContraseña(usuario, contraseña);
    }

}