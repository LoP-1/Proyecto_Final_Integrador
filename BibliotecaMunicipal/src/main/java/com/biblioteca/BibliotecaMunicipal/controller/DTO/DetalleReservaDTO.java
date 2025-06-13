package com.biblioteca.BibliotecaMunicipal.controller.DTO;

public class DetalleReservaDTO {
    private Integer id;
    private Integer libroId;
    private String tituloLibro; // Solo en respuestas

    public DetalleReservaDTO() {}
    public DetalleReservaDTO(Integer id, Integer libroId, String tituloLibro) {
        this.id = id;
        this.libroId = libroId;
        this.tituloLibro = tituloLibro;
    }
    // getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLibroId() {
        return libroId;
    }

    public void setLibroId(Integer libroId) {
        this.libroId = libroId;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }
}