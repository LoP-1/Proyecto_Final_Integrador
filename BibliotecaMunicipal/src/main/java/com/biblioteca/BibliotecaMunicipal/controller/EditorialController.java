package com.biblioteca.BibliotecaMunicipal.controller;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.EditorialDTO;
import com.biblioteca.BibliotecaMunicipal.sevice.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public ResponseEntity<List<EditorialDTO>> listarEditoriales() {
        return ResponseEntity.ok(editorialService.listarEditoriales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialDTO> obtenerEditorialPorId(@PathVariable Integer id) {
        Optional<EditorialDTO> editorial = editorialService.obtenerEditorialPorId(id);
        return editorial.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EditorialDTO> crearEditorial(@RequestBody EditorialDTO dto) {
        EditorialDTO creada = editorialService.crearEditorial(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditorialDTO> actualizarEditorial(@PathVariable Integer id, @RequestBody EditorialDTO dto) {
        Optional<EditorialDTO> actualizada = editorialService.actualizarEditorial(id, dto);
        return actualizada.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEditorial(@PathVariable Integer id) {
        if (editorialService.eliminarEditorial(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}