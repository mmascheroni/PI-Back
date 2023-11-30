package com.backend.PIBack.controller;

import com.backend.PIBack.entity.Reserva;
import com.backend.PIBack.service.impl.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Reserva> registrarReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.registrarReserva(reserva));
    }

}
