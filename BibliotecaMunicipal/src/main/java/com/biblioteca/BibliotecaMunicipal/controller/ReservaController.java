package com.biblioteca.BibliotecaMunicipal.controller;

import com.biblioteca.BibliotecaMunicipal.controller.DTO.ReservaDTO;
import com.biblioteca.BibliotecaMunicipal.sevice.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorId(@PathVariable Integer id) {
        Optional<ReservaDTO> dto = reservaService.obtenerReservaPorId(id);
        return dto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorIdUsuario(@PathVariable Integer id) {
        List<ReservaDTO> reservas = reservaService.obtenerReservasPorIdUsuario(id);
        if (reservas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO dto) {
        ReservaDTO creada = reservaService.crearReserva(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<ReservaDTO> marcarDevuelta(
            @PathVariable Integer id,
            @RequestBody(required = false) DevolucionRequest request) {
        LocalDate fechaDev = request != null ? request.getFechaDevolucionReal() : null;
        Optional<ReservaDTO> devuelta = reservaService.marcarDevuelta(id, fechaDev);
        return devuelta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Integer id) {
        if (reservaService.eliminarReserva(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // DTO para devolver la reserva (opcional enviar fecha)
    public static class DevolucionRequest {
        private LocalDate fechaDevolucionReal;
        public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }
        public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) { this.fechaDevolucionReal = fechaDevolucionReal; }
    }
}