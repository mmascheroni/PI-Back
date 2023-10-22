package com.backend.PIBack.controller;

import com.backend.PIBack.dto.ImagenDto;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.service.impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/imagen")
public class ImagenController {

    private ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<ImagenDto> registarImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.registrarImagen(imagen));
    }

    //GET
    @GetMapping
    public List<ImagenDto> listarImagenes() {
        return imagenService.listarImagenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenDto> buscarImagenPorId(@PathVariable Long id) {
        ImagenDto imagenEncontrada = imagenService.buscarImagenPorId(id);
        return ResponseEntity.ok(imagenEncontrada);
    }


    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable Long id) {
        imagenService.eliminarImagen(id);
        return ResponseEntity.ok("Se ha eliminado la imagen");
    }
}
