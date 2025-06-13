package com.biblioteca.BibliotecaMunicipal.controller;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.LibroDTO;
import com.biblioteca.BibliotecaMunicipal.sevice.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Listar libros con filtros opcionales
    @GetMapping
    public ResponseEntity<List<LibroDTO>> listarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String autor
    ) {
        return ResponseEntity.ok(libroService.listarLibros(titulo, categoria, autor));
    }

    // Obtener detalle de libro
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerLibroPorId(@PathVariable Integer id) {
        Optional<LibroDTO> dto = libroService.obtenerLibroPorId(id);
        return dto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear libro (admin)
    @PostMapping
    public ResponseEntity<LibroDTO> crearLibro(@RequestBody LibroDTO dto) {
        LibroDTO guardado = libroService.crearLibro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // Editar libro (admin)
    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> actualizarLibro(@PathVariable Integer id, @RequestBody LibroDTO dto) {
        Optional<LibroDTO> actualizado = libroService.actualizarLibro(id, dto);
        return actualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar libro (admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Integer id) {
        if (libroService.eliminarLibro(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Consultar disponibilidad
    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<?> consultarDisponibilidad(@PathVariable Integer id) {
        Optional<Boolean> disponible = libroService.consultarDisponibilidad(id);
        return disponible
                .map(disp -> ResponseEntity.ok().body(new DisponibilidadResponse(disp)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Clase para respuesta de disponibilidad
    public static class DisponibilidadResponse {
        private boolean disponible;
        public DisponibilidadResponse(boolean disponible) {
            this.disponible = disponible;
        }
        public boolean isDisponible() { return disponible; }
        public void setDisponible(boolean disponible) { this.disponible = disponible; }
    }
}