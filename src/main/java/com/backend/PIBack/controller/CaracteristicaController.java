package com.backend.PIBack.controller;

import com.backend.PIBack.dto.CaracteristicaDto;
import com.backend.PIBack.entity.Caracteristica;
import com.backend.PIBack.service.impl.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/caracteristicas")
public class CaracteristicaController {

    private CaracteristicaService caracteristicaService;

    @Autowired
    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<CaracteristicaDto> registarCaracteristica(@RequestBody Caracteristica caracteristica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaService.registrarCaracteristica(caracteristica));
    }

    //GET
    @GetMapping
    public List<CaracteristicaDto> listarCaracteristicas() {
        return caracteristicaService.listarCaracteristicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaDto> buscarCaracteristicaPorId(@PathVariable Long id) {
        CaracteristicaDto caracteristicaEncontrada = caracteristicaService.buscarCaracteristicaPorId(id);
        return ResponseEntity.ok(caracteristicaEncontrada);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<CaracteristicaDto> buscarCaracteristicaPorNombre(@PathVariable String nombre) {
        CaracteristicaDto caracteristicaEncontrada = caracteristicaService.buscarCaracteristicaPorNombre(nombre);
        return ResponseEntity.ok(caracteristicaEncontrada);
    }


    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<CaracteristicaDto> actualizarCaracteristica(@RequestBody Caracteristica caracteristica) {
        CaracteristicaDto caracteristicaActualizada = caracteristicaService.actualizarCaracteristica(caracteristica);
        return ResponseEntity.ok(caracteristicaActualizada);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCaracteristica(@PathVariable Long id) {
        caracteristicaService.eliminarCaracteristica(id);
        return ResponseEntity.ok("Se ha eliminado la característica.");
    }
}
