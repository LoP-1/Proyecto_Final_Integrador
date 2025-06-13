package com.biblioteca.BibliotecaMunicipal.controller.DTO;

import java.time.LocalDate;
import java.util.List;

public class ReservaDTO {
    private Integer id;
    private String estado;
    private LocalDate fechaReserva;
    private LocalDate fechaDevolucion;
    private LocalDate fechaDevolucionReal;
    private Integer usuarioId;
    private String usuarioNombre; // Solo en respuestas
    private List<Integer> librosIds; // Solo en creación/edición
    private List<DetalleReservaDTO> detalles; // Solo en respuestas

    public ReservaDTO() {}
    // constructor completo, getters y setters...

    public ReservaDTO(Integer id, String estado, LocalDate fechaReserva, LocalDate fechaDevolucion, LocalDate fechaDevolucionReal, Integer usuarioId, String usuarioNombre, List<Integer> librosIds, List<DetalleReservaDTO> detalles) {
        this.id = id;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.librosIds = librosIds;
        this.detalles = detalles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public List<Integer> getLibrosIds() {
        return librosIds;
    }

    public void setLibrosIds(List<Integer> librosIds) {
        this.librosIds = librosIds;
    }

    public List<DetalleReservaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleReservaDTO> detalles) {
        this.detalles = detalles;
    }
}
