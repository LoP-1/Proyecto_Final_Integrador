package com.biblioteca.BibliotecaMunicipal.sevice;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.LibroDTO;
import com.biblioteca.BibliotecaMunicipal.model.*;
import com.biblioteca.BibliotecaMunicipal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    public List<LibroDTO> listarLibros(String titulo, String categoria, String autor) {
        List<Libro> libros = libroRepository.findAll();

        if (titulo != null && !titulo.isEmpty()) {
            libros = libros.stream()
                    .filter(l -> l.getTitulo() != null && l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (categoria != null && !categoria.isEmpty()) {
            libros = libros.stream()
                    .filter(l -> l.getCategoria() != null && l.getCategoria().getNombre().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }
        if (autor != null && !autor.isEmpty()) {
            libros = libros.stream()
                    .filter(l -> l.getAutores() != null && l.getAutores().stream()
                            .anyMatch(a -> a.getNombre().equalsIgnoreCase(autor)))
                    .collect(Collectors.toList());
        }

        return libros.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<LibroDTO> obtenerLibroPorId(Integer id) {
        return libroRepository.findById(id).map(this::toDTO);
    }

    public LibroDTO crearLibro(LibroDTO dto) {
        Libro libro = fromDTO(dto);
        libro = libroRepository.save(libro);
        return toDTO(libro);
    }

    public Optional<LibroDTO> actualizarLibro(Integer id, LibroDTO dto) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(dto.getTitulo());
            libro.setDescripcion(dto.getDescripcion());
            libro.setPortada(dto.getPortada());
            libro.setDisponibilidad(dto.getDisponibilidad());
            libro.setTipo(dto.getTipo());

            if (dto.getCategoriaId() != null) {
                categoriaRepository.findById(dto.getCategoriaId()).ifPresent(libro::setCategoria);
            }
            if (dto.getEditorialId() != null) {
                editorialRepository.findById(dto.getEditorialId()).ifPresent(libro::setEditorial);
            }
            if (dto.getAutoresIds() != null && !dto.getAutoresIds().isEmpty()) {
                List<Autor> autores = autorRepository.findAllById(dto.getAutoresIds());
                libro.setAutores(autores);
            }

            Libro actualizado = libroRepository.save(libro);
            return toDTO(actualizado);
        });
    }

    public boolean eliminarLibro(Integer id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Boolean> consultarDisponibilidad(Integer id) {
        return libroRepository.findById(id).map(Libro::getDisponibilidad);
    }

    // Conversión de entidad a DTO
    private LibroDTO toDTO(Libro libro) {
        List<Integer> autoresIds = libro.getAutores() != null
                ? libro.getAutores().stream().map(Autor::getId).collect(Collectors.toList())
                : Collections.emptyList();
        List<String> autoresNombres = libro.getAutores() != null
                ? libro.getAutores().stream().map(Autor::getNombre).collect(Collectors.toList())
                : Collections.emptyList();

        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getDescripcion(),
                libro.getPortada(),
                libro.getDisponibilidad(),
                libro.getTipo(),
                libro.getCategoria() != null ? libro.getCategoria().getId() : null,
                libro.getCategoria() != null ? libro.getCategoria().getNombre() : null,
                libro.getEditorial() != null ? libro.getEditorial().getId() : null,
                libro.getEditorial() != null ? libro.getEditorial().getNombre() : null,
                autoresIds,
                autoresNombres
        );
    }

    // Conversión de DTO a entidad
    private Libro fromDTO(LibroDTO dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setDescripcion(dto.getDescripcion());
        libro.setPortada(dto.getPortada());
        libro.setDisponibilidad(dto.getDisponibilidad());
        libro.setTipo(dto.getTipo());

        if (dto.getCategoriaId() != null) {
            categoriaRepository.findById(dto.getCategoriaId()).ifPresent(libro::setCategoria);
        }
        if (dto.getEditorialId() != null) {
            editorialRepository.findById(dto.getEditorialId()).ifPresent(libro::setEditorial);
        }
        if (dto.getAutoresIds() != null && !dto.getAutoresIds().isEmpty()) {
            List<Autor> autores = autorRepository.findAllById(dto.getAutoresIds());
            libro.setAutores(autores);
        }
        return libro;
    }
}