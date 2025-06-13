package com.biblioteca.BibliotecaMunicipal.controller.DTO;

public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String dni;
    private String rol;
    private String Contraseña;
    // Constructor vacío
    public UsuarioDTO() {}

    // Constructor con parámetros
    public UsuarioDTO(Integer id, String nombre, String apellido, String correo, String dni, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.rol = rol;
    }

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}