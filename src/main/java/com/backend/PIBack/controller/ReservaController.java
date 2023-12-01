package com.backend.PIBack.controller;

import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.entity.Reserva;
import com.backend.PIBack.service.impl.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<ReservaDto> registrarReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.registrarReserva(reserva));
    }

    //GET
    @GetMapping
    public List<ReservaDto> listarReservas(){
        return reservaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> buscarReservaPorId(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.buscarReservaPorId(id));
    }


}