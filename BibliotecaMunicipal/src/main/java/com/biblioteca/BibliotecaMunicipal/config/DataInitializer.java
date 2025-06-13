package com.biblioteca.BibliotecaMunicipal.config;

import com.biblioteca.BibliotecaMunicipal.model.Rol;
import com.biblioteca.BibliotecaMunicipal.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;

    public DataInitializer(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findByNombre("USER").isEmpty()) {
            rolRepository.save(new Rol(null, "USER"));
        }
        if (rolRepository.findByNombre("ADMIN").isEmpty()) {
            rolRepository.save(new Rol(null, "ADMIN"));
        }
        // Agrega más roles si lo necesitas
        System.out.println("Roles inicializados");
    }
}