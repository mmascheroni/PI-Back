package com.backend.PIBack.controller;

import com.backend.PIBack.dto.ImagenDto;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.service.impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/imagen")
public class ImagenController {

    private ImagenService imagenService;

    @Autowired
    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    //POST
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<ImagenDto> registarImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.registrarImagen(imagen));
    }

    //GET
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<ImagenDto> listarImagenes() {
        return imagenService.listarImagenes();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ImagenDto> buscarImagenPorId(@PathVariable Long id) {
        ImagenDto imagenEncontrada = imagenService.buscarImagenPorId(id);
        return ResponseEntity.ok(imagenEncontrada);
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<ImagenDto> actualizarImagen(@RequestBody Imagen imagen) {
        ImagenDto imagenActualizada = imagenService.actualizarImagen(imagen);
        return ResponseEntity.ok(imagenActualizada);
    }

    //DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable Long id) {
        imagenService.eliminarImagen(id);
        return ResponseEntity.ok("Se ha eliminado la imagen");
    }
}
