package com.biblioteca.BibliotecaMunicipal.sevice;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.AutorDTO;
import com.biblioteca.BibliotecaMunicipal.model.Autor;
import com.biblioteca.BibliotecaMunicipal.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> listarAutores() {
        return autorRepository.findAll()
                .stream()
                .map(a -> new AutorDTO(a.getId(), a.getNombre()))
                .collect(Collectors.toList());
    }

    public Optional<AutorDTO> obtenerAutorPorId(Integer id) {
        return autorRepository.findById(id)
                .map(a -> new AutorDTO(a.getId(), a.getNombre()));
    }

    public AutorDTO crearAutor(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        return new AutorDTO(autorRepository.save(autor).getId(), autor.getNombre());
    }

    public Optional<AutorDTO> actualizarAutor(Integer id, AutorDTO dto) {
        return autorRepository.findById(id).map(autor -> {
            autor.setNombre(dto.getNombre());
            autorRepository.save(autor);
            return new AutorDTO(autor.getId(), autor.getNombre());
        });
    }

    public boolean eliminarAutor(Integer id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}