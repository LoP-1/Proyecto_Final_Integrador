package com.biblioteca.BibliotecaMunicipal.repository;

import com.biblioteca.BibliotecaMunicipal.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva,Integer> {
    List<Reserva> findAllByUsuario_Id(Integer usuarioId);
}
