package com.biblioteca.BibliotecaMunicipal.controller;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.UsuarioDTO;
import com.biblioteca.BibliotecaMunicipal.controller.DTO.LoginRequestDTO;
import com.biblioteca.BibliotecaMunicipal.model.Usuario;
import com.biblioteca.BibliotecaMunicipal.sevice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(id);
        return usuarioOpt
                .map(usuario -> ResponseEntity.ok(toDTO(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar usuario
    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioGuardado = usuarioService.registrarUsuario(usuarioDTO);

        UsuarioDTO respuesta = toDTO(usuarioGuardado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MensajeUsuarioCreado("Usuario creado correctamente", respuesta));
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOpt = usuarioService.actualizarUsuario(id, usuarioDTO);
        if (usuarioOpt.isPresent()) {
            UsuarioDTO respuesta = toDTO(usuarioOpt.get());
            return ResponseEntity.ok(new MensajeUsuarioCreado("Usuario actualizado correctamente", respuesta));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensajeUsuarioCreado("Usuario no encontrado", null));
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        if (usuarioService.eliminarUsuario(id)) {
            return ResponseEntity.ok(new MensajeUsuarioCreado("Usuario eliminado correctamente", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensajeUsuarioCreado("Usuario no encontrado", null));
        }
    }

    // Utilidad para convertir entidad a DTO
    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getDni(),
                usuario.getRol() != null ? usuario.getRol().getNombre() : null
        );
    }

    // Clase interna para envolver el mensaje y el usuario creado/actualizado/eliminado
    public static class MensajeUsuarioCreado {
        private String mensaje;
        private UsuarioDTO usuario;

        public MensajeUsuarioCreado(String mensaje, UsuarioDTO usuario) {
            this.mensaje = mensaje;
            this.usuario = usuario;
        }

        public String getMensaje() {
            return mensaje;
        }

        public UsuarioDTO getUsuario() {
            return usuario;
        }
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.login(loginRequest.getUsuario(), loginRequest.getContraseña());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return ResponseEntity.ok(toDTO(usuario));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MensajeUsuarioCreado("Credenciales inválidas", null));
        }
    }
}