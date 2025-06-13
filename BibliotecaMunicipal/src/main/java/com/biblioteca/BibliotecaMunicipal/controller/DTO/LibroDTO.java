package com.biblioteca.BibliotecaMunicipal.controller.DTO;

import java.util.List;

public class LibroDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String portada;
    private Boolean disponibilidad;
    private String tipo;
    private Integer categoriaId;
    private String categoriaNombre;
    private Integer editorialId;
    private String editorialNombre;
    private List<Integer> autoresIds;
    private List<String> autoresNombres;

    public LibroDTO() {}

    public LibroDTO(Integer id, String titulo, String descripcion, String portada, Boolean disponibilidad, String tipo,
                    Integer categoriaId, String categoriaNombre,
                    Integer editorialId, String editorialNombre,
                    List<Integer> autoresIds, List<String> autoresNombres) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.portada = portada;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.editorialId = editorialId;
        this.editorialNombre = editorialNombre;
        this.autoresIds = autoresIds;
        this.autoresNombres = autoresNombres;
    }

    // Getters y setters aquí...


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Integer getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(Integer editorialId) {
        this.editorialId = editorialId;
    }

    public String getEditorialNombre() {
        return editorialNombre;
    }

    public void setEditorialNombre(String editorialNombre) {
        this.editorialNombre = editorialNombre;
    }

    public List<Integer> getAutoresIds() {
        return autoresIds;
    }

    public void setAutoresIds(List<Integer> autoresIds) {
        this.autoresIds = autoresIds;
    }

    public List<String> getAutoresNombres() {
        return autoresNombres;
    }

    public void setAutoresNombres(List<String> autoresNombres) {
        this.autoresNombres = autoresNombres;
    }
}