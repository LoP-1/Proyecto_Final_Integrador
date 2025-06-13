package com.biblioteca.BibliotecaMunicipal.repository;

import com.biblioteca.BibliotecaMunicipal.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Integer> {
}
