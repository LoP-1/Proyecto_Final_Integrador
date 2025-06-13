package com.biblioteca.BibliotecaMunicipal.controller;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.AutorDTO;
import com.biblioteca.BibliotecaMunicipal.sevice.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        return ResponseEntity.ok(autorService.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obtenerAutorPorId(@PathVariable Integer id) {
        Optional<AutorDTO> autor = autorService.obtenerAutorPorId(id);
        return autor.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutorDTO> crearAutor(@RequestBody AutorDTO dto) {
        AutorDTO creado = autorService.crearAutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> actualizarAutor(@PathVariable Integer id, @RequestBody AutorDTO dto) {
        Optional<AutorDTO> actualizado = autorService.actualizarAutor(id, dto);
        return actualizado.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAutor(@PathVariable Integer id) {
        if (autorService.eliminarAutor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}