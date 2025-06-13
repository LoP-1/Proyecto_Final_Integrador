package com.biblioteca.BibliotecaMunicipal.repository;

import com.biblioteca.BibliotecaMunicipal.model.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SancionRepository extends JpaRepository<Sancion,Integer> {
}
