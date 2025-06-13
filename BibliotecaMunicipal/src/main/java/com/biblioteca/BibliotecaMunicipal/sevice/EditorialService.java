package com.biblioteca.BibliotecaMunicipal.sevice;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.EditorialDTO;
import com.biblioteca.BibliotecaMunicipal.model.Editorial;
import com.biblioteca.BibliotecaMunicipal.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public List<EditorialDTO> listarEditoriales() {
        return editorialRepository.findAll()
                .stream()
                .map(e -> new EditorialDTO(e.getId(), e.getNombre()))
                .collect(Collectors.toList());
    }

    public Optional<EditorialDTO> obtenerEditorialPorId(Integer id) {
        return editorialRepository.findById(id)
                .map(e -> new EditorialDTO(e.getId(), e.getNombre()));
    }

    public EditorialDTO crearEditorial(EditorialDTO dto) {
        Editorial editorial = new Editorial();
        editorial.setNombre(dto.getNombre());
        return new EditorialDTO(editorialRepository.save(editorial).getId(), editorial.getNombre());
    }

    public Optional<EditorialDTO> actualizarEditorial(Integer id, EditorialDTO dto) {
        return editorialRepository.findById(id).map(editorial -> {
            editorial.setNombre(dto.getNombre());
            editorialRepository.save(editorial);
            return new EditorialDTO(editorial.getId(), editorial.getNombre());
        });
    }

    public boolean eliminarEditorial(Integer id) {
        if (editorialRepository.existsById(id)) {
            editorialRepository.deleteById(id);
            return true;
        }
        return false;
    }
}