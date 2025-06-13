package com.biblioteca.BibliotecaMunicipal.repository;

import com.biblioteca.BibliotecaMunicipal.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
