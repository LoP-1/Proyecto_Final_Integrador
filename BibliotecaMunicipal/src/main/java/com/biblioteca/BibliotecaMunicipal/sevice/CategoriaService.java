package com.biblioteca.BibliotecaMunicipal.sevice;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.CategoriaDTO;
import com.biblioteca.BibliotecaMunicipal.model.Categoria;
import com.biblioteca.BibliotecaMunicipal.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(c -> new CategoriaDTO(c.getId(), c.getNombre()))
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> obtenerCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id)
                .map(c -> new CategoriaDTO(c.getId(), c.getNombre()));
    }

    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        return new CategoriaDTO(categoriaRepository.save(categoria).getId(), categoria.getNombre());
    }

    public Optional<CategoriaDTO> actualizarCategoria(Integer id, CategoriaDTO dto) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(dto.getNombre());
            categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria.getId(), categoria.getNombre());
        });
    }

    public boolean eliminarCategoria(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}