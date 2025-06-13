package com.biblioteca.BibliotecaMunicipal.sevice;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.ReservaDTO;
import com.biblioteca.BibliotecaMunicipal.controller.DTO.DetalleReservaDTO;
import com.biblioteca.BibliotecaMunicipal.model.*;
import com.biblioteca.BibliotecaMunicipal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private DetalleReservaRepository detalleReservaRepository;

    public List<ReservaDTO> listarReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservaDTO> obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id).map(this::toDTO);
    }

    public ReservaDTO crearReserva(ReservaDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setEstado(Reserva.EstadoReserva.PENDIENTE);
        reserva.setFechaReserva(LocalDate.now());
        reserva.setFechaDevolucion(dto.getFechaDevolucion());

        // Usuario
        usuarioRepository.findById(dto.getUsuarioId()).ifPresent(reserva::setUsuario);

        // Guardar reserva principal primero
        reserva = reservaRepository.save(reserva);

        // DetalleReserva y actualización de disponibilidad
        List<DetalleReserva> detalles = new ArrayList<>();
        for (Integer libroId : dto.getLibrosIds()) {
            Optional<Libro> libroOpt = libroRepository.findById(libroId);
            if (libroOpt.isPresent()) {
                Libro libro = libroOpt.get();
                // Marcar como no disponible
                libro.setDisponibilidad(false);
                libroRepository.save(libro);

                DetalleReserva dr = new DetalleReserva();
                dr.setLibro(libro);
                dr.setReserva(reserva);
                detalles.add(detalleReservaRepository.save(dr));
            }
        }
        reserva.setDetallesReserva(detalles);
        return toDTO(reserva);
    }

    public List<ReservaDTO> obtenerReservasPorIdUsuario(Integer id) {
        return reservaRepository.findAllByUsuario_Id(id)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public Optional<ReservaDTO> marcarDevuelta(Integer id, LocalDate fechaDevolucionReal) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.setEstado(Reserva.EstadoReserva.DEVUELTO);
            reserva.setFechaDevolucionReal(fechaDevolucionReal != null ? fechaDevolucionReal : LocalDate.now());

            // Hacer libros disponibles de nuevo
            for (DetalleReserva dr : reserva.getDetallesReserva()) {
                Libro libro = dr.getLibro();
                libro.setDisponibilidad(true);
                libroRepository.save(libro);
            }

            reservaRepository.save(reserva);
            return toDTO(reserva);
        });
    }

    public boolean eliminarReserva(Integer id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- Métodos de conversión ---

    private ReservaDTO toDTO(Reserva reserva) {
        List<DetalleReservaDTO> detalles = reserva.getDetallesReserva() != null
                ? reserva.getDetallesReserva().stream()
                .map(dr -> new DetalleReservaDTO(
                        dr.getId(),
                        dr.getLibro().getId(),
                        dr.getLibro().getTitulo()))
                .collect(Collectors.toList())
                : Collections.emptyList();

        String usuarioNombre = reserva.getUsuario() != null ? reserva.getUsuario().getNombre() : null;
        Integer usuarioId = reserva.getUsuario() != null ? reserva.getUsuario().getId() : null;

        return new ReservaDTO(
                reserva.getId(),
                reserva.getEstado().name(),
                reserva.getFechaReserva(),
                reserva.getFechaDevolucion(),
                reserva.getFechaDevolucionReal(),
                usuarioId,
                usuarioNombre,
                null, // librosIds solo para creación
                detalles
        );
    }
}